package de.hpk;

import de.hpk.matrix.IMatrixAlgorithm;
import de.hpk.matrix.Matrix;
import de.hpk.matrix.MatrixAlgorithm2;
import de.hpk.matrix.MatrixAlgorithm4;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

public class MatrixTest {

    // 1. Zeile = row, 2. Spalte = column
    private static Matrix matrixLeft;

    private static Matrix matrixRight;

    private static Matrix matrixResult;

    private static Matrix[] matrixAValues = new Matrix[4];

    private static Matrix[] matrixBValues = new Matrix[4];

    private static Matrix[] matrixResults = new Matrix[4];

    private static IMatrixAlgorithm[] matrixAlgorithms = new IMatrixAlgorithm[]{new MatrixAlgorithm2(), new MatrixAlgorithm4()};

    private static long timeForSingleThread;

    private static final double EPS = 1.E-8;

    private static long nsToMs(long time) {
        return time / 1000/*mikrosekunden*/ / 1000/*millisekunden*/;
    }

    @BeforeClass
    public static void setUp() {
        System.out.println("Setup tests");
        //matrixLeft = new Matrix(2048, 2048);
        matrixLeft = new Matrix(128, 128);
        fillMatrix(matrixLeft);
        matrixRight = new Matrix(128, 128);
        fillMatrix(matrixRight);
        long time = System.nanoTime();
        matrixResult = matrixLeft.multiply(matrixRight);
        timeForSingleThread = System.nanoTime() - time;
        System.out.println("speed single threaded:" + (timeForSingleThread / 1000 / 1000));
        Assert.assertEquals(matrixLeft, matrixLeft);
        Assert.assertEquals(matrixRight, matrixRight);
        Assert.assertEquals(matrixResult, matrixResult);
        Assert.assertNotEquals(matrixResult, matrixRight);
        System.out.println("Setup tests done");

        for (int i = 0, length = matrixResults.length; i < length; i++) {
            int valueOne = (int) Math.pow(2, i + 1);
            int valueTwo = (int) Math.pow(2, i + 2);
            int valueThree = (int) Math.pow(2, i + 3);
            System.out.println("multiply:" + valueOne + " x " + valueTwo + " with " + valueTwo + " x " + valueThree);
            Matrix a = new Matrix(valueOne, valueTwo);
            Matrix b = new Matrix(valueTwo, valueThree);
            fillMatrix(a);
            fillMatrix(b);
            matrixAValues[i] = a;
            matrixBValues[i] = b;
            matrixResults[i] = a.multiply(b);
        }
    }

    @Test
    public void testTranspose() {
        Matrix matrix = new Matrix(100, 100);
        fillMatrix(matrix);
        Assert.assertEquals(matrix.transpose().transpose(), matrix);
        Random random = new Random();
        matrix = new Matrix(Math.abs(random.nextInt() % 1000), Math.abs(random.nextInt() % 1000));
        fillMatrix(matrix);
        Assert.assertEquals(matrix.transpose().transpose(), matrix);
    }

    @Test
    public void testTransposeZeroRows() {
        Random random = new Random();
        int columns = Math.abs(random.nextInt() % 1000);
        Matrix matrix = new Matrix(0, columns);
        fillMatrix(matrix);
        Assert.assertEquals(matrix, matrix.transpose());
    }

    @Test
    public void testTransposeZeroColumns() {
        Random random = new Random();
        int rows = Math.abs(random.nextInt() % 1000);
        Matrix matrix = new Matrix(rows, 0);
        fillMatrix(matrix);
        Assert.assertEquals(matrix, matrix.transpose());
    }

    @Test
    public void testTransposeZeroRowsAndColumns() {
        Matrix matrix = new Matrix(0, 0);
        fillMatrix(matrix);
        Assert.assertEquals(matrix, matrix.transpose());
    }

    @Test
    public void testTransposeRowAndColumnsCount() {
        Random random = new Random();
        int rows = Math.abs((random.nextInt() % 1000) + 1);
        int columns = Math.abs((random.nextInt() % 1000) + 1);
        Matrix matrix = new Matrix(rows, columns);
        fillMatrix(matrix);
        Matrix transposedMatrix = matrix.transpose();
        Assert.assertEquals(matrix.rows, transposedMatrix.columns);
        Assert.assertEquals(matrix.columns, transposedMatrix.rows);
    }

    @Test
    public void testTransposeValues() {
        Random random = new Random();
        int rows = Math.abs(random.nextInt() % 1000);
        int columns = Math.abs(random.nextInt() % 1000);
        Matrix matrix = new Matrix(rows, columns);
        fillMatrix(matrix);
        Matrix transposedMatrix = matrix.transpose();
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                Assert.assertEquals(matrix.values[i][j], transposedMatrix.values[j][i], EPS);
            }
        }
    }

    @Test
    public void testToString() {
        Matrix matrix = new Matrix(2, 2);
        matrix.values[0][0] = 1;
        matrix.values[0][1] = 0;
        matrix.values[1][0] = 0;
        matrix.values[1][1] = 1;
        Assert.assertEquals("1.0,0.0\n0.0,1.0\n", matrix.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeRows() {
        Matrix negativeRows = new Matrix(-100, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeColumns() {
        Matrix negativeRows = new Matrix(100, -100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeRowsAndColumns() {
        Matrix negativeRows = new Matrix(-100, -100);
    }

    @Test
    public void testEqualsWrongObjectType() {
        Random random = new Random();
        int firstRows = Math.abs(random.nextInt() % 1000);
        int firstColumns = Math.abs(random.nextInt() % 1000);
        Assert.assertNotEquals(new Matrix(firstRows, firstColumns), 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestMultiplyNotAllowed() {
        Random random = new Random();
        int firstRows = Math.abs(random.nextInt() % 1000);
        int firstColumns = Math.abs(random.nextInt() % 1000);
        int secondRows = firstRows + 1;
        int secondColumns = Math.abs(random.nextInt() % 1000);
        Matrix matrix = new Matrix(firstRows, firstColumns);
        Matrix matrixTwo = new Matrix(secondRows, secondColumns);
        matrix.multiply(matrixTwo);
    }

    @Test
    public void TestMultiplyRandomRowsColumns() {
        Random random = new Random();
        Matrix[] twoRandomMatrix = createTwoRandomMatrix(Math.abs(random.nextInt() % 100));
        Matrix a = twoRandomMatrix[0];
        Matrix b = twoRandomMatrix[1];
        Matrix result = a.multiply(b);
        testAlgorithms(a, b, result);
    }

    @Test
    public void TestAlgorithms() {
        for (int i = 0, length = matrixAlgorithms.length; i < length; i++) {
            IMatrixAlgorithm matrixAlgorithm = matrixAlgorithms[i];
            long time = System.nanoTime();
            Matrix c = matrixAlgorithm.multiply(matrixLeft, matrixRight);
            long timeForAlgorithm = System.nanoTime() - time;
            Assert.assertEquals(matrixResult, c);
            System.out.println("Test" + matrixAlgorithm.getName());
            System.out.println("n" + " | " + "seq ms" + " | " + "par ms" + " | " + "speedup");
            System.out.println(matrixLeft.rows + " | " + nsToMs(timeForSingleThread) + " | " + nsToMs(timeForAlgorithm) + " | " + ((float) timeForSingleThread / (float) timeForAlgorithm) + "x");
            System.out.println("----------");

            for (int j = 0, lengthJ = matrixResults.length; j < lengthJ; j++) {
                Assert.assertEquals(matrixResults[j], matrixAlgorithm.multiply(matrixAValues[j], matrixBValues[j]));
            }
        }
    }

    /*@Test
    public void TestAlgorithm1Serial() {
        System.out.println("Test 1");
        Matrix c = new Matrix(matrixLeft.rows, matrixRight.columns);
        long time = System.nanoTime();

        int i, j, k, lengthI, lengthJ, lengthK;
        for (i = 0, lengthI = matrixLeft.rows; i < lengthI; i++) {
            for (j = 0, lengthJ = matrixRight.columns; j < lengthJ; j++) {
                for (k = 0, lengthK = matrixLeft.columns; k < lengthK; k++) {
                    c.values[i][j] += matrixLeft.values[i][k] * matrixRight.values[k][j];
                }
            }
        }

        long timeForAlgorithm1 = System.nanoTime() - time;
        Assert.assertEquals(matrixResult, c);
        System.out.println("Test 1 done");
        System.out.println("speed algorithm1:" + (timeForAlgorithm1 / 1000 / 1000));
        System.out.println("speedup ms:" + nsToMs(timeForSingleThread - timeForAlgorithm1));
    }*/


    private static void fillMatrix(Matrix matrix) {
        Random random = new Random();
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                matrix.values[i][j] = random.nextInt() % 15;
            }
        }
    }

    private static Matrix[] createTwoRandomMatrix(int n) {
        Matrix[] result = new Matrix[2];
        Matrix a = new Matrix(n, n + 1);
        fillMatrix(a);
        Matrix b = new Matrix(n + 1, n);
        fillMatrix(b);
        result[0] = a;
        result[1] = b;
        return result;
    }

    private static void testAlgorithms(Matrix a, Matrix b, Matrix expected) {
        for (int i = 0, length = matrixAlgorithms.length; i < length; i++) {
            IMatrixAlgorithm matrixAlgorithm = matrixAlgorithms[i];
            long time = System.nanoTime();
            Matrix c = matrixAlgorithm.multiply(a, b);
            long timeForAlgorithm = System.nanoTime() - time;
            Assert.assertEquals(expected, c);
            System.out.println("speed:" + matrixAlgorithm.getName() + " " + nsToMs(timeForAlgorithm) + "ms " + "(" + a.rows + "," + a.columns + ")*(" + b.rows + "," + b.columns + ")");
            System.out.println("----------");
        }
    }
}
