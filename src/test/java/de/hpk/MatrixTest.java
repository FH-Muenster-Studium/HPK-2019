package de.hpk;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MatrixTest {

    // 1. Zeile = row, 2. Spalte = column
    Matrix matrixLeft;

    Matrix matrixRight;

    Matrix matrixResult;

    private final Executor threadPoolExecutor = Executors.newFixedThreadPool(8);

    class MatrixWorker implements Runnable {

        private final int row;

        private final Matrix result;

        private final Matrix left, right;

        private final Semaphore semaphore;

        public MatrixWorker(int row, Matrix left, Matrix right, Matrix result, Semaphore semaphore) {
            this.row = row;
            this.left = left;
            this.right = right;
            this.result = result;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            for (int j = 0; j < 2; j++) {
                result.values[row][j] = vectorMultiplication(left.values[row], right.values[j]);
            }
            this.semaphore.release();
        }
    }

    public class Matrix {

        public final int rows;

        public final int columns;

        public double[][] values;

        public Matrix(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            this.values = new double[rows][columns];
        }

        public Matrix(Matrix matrix) {
            this.rows = matrix.rows;
            this.columns = matrix.columns;
            this.values = matrix.values;
        }

        private Matrix transpose() {
            if (rows == 0 || columns == 0) return new Matrix(this);
            Matrix transposed = new Matrix(columns, rows);
            for (int i = 0; i < values.length; i++)
                for (int j = 0; j < values[0].length; j++)
                    transposed.values[j][i] = values[i][j];
            return transposed;
        }
    }

    @Before
    public final void setUp() {
        System.out.println("Setup tests");
        matrixLeft = new Matrix(2, 2);
        fillMatrix(matrixLeft);
        printMatrix(matrixLeft);
        System.out.println("-----");
        matrixRight = new Matrix(2, 2);
        fillMatrix(matrixRight);
        printMatrix(matrixRight);
        System.out.println("-----");
        matrixResult = new Matrix(2, 2);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    matrixResult.values[i][j] += matrixLeft.values[i][k] * matrixRight.values[k][j];
                }
            }
        }
        printMatrix(matrixResult);
        System.out.println("-----");
        Assert.assertTrue(equalsMatrix(matrixLeft, matrixLeft));
        Assert.assertTrue(equalsMatrix(matrixRight, matrixRight));
        Assert.assertTrue(equalsMatrix(matrixResult, matrixResult));
        Assert.assertFalse(equalsMatrix(matrixResult, matrixLeft));
        System.out.println("Setup tests done");
    }

    @Test
    public void TestAlgorithm3() throws InterruptedException {
        System.out.println("Test 3");
        printMatrix(matrixLeft);
        System.out.println("-----");
        printMatrix(matrixRight);
        System.out.println("-----");
        Matrix r = matrixRight.transpose();
        Matrix c = new Matrix(2, 2);
        Semaphore semaphore = new Semaphore(matrixLeft.rows, false);
        for (int i = 0; i < matrixLeft.rows; i++) {
            threadPoolExecutor.execute(new MatrixWorker(i, matrixLeft, r, c, semaphore));
        }
        System.out.println("semaphore.acquire()");
        semaphore.acquire();
        System.out.println("semaphore.acquire() done");
        printMatrix(c);
        System.out.println("-----");
        Assert.assertTrue(equalsMatrix(matrixResult, c));
        System.out.println("Test 3 done");
    }

    private double vectorMultiplication(double[] vector1, double[] vector2) {
        int length = vector1.length;
        double result = 0;
        for (int i = 0; i < length; i++) {
            result += vector1[i] * vector2[i];
        }
        return result;
    }

    private void fillMatrix(Matrix matrix) {
        Random random = new Random();
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                matrix.values[i][j] = random.nextInt() % 15;
            }
        }
    }

    private void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                System.out.print(matrix.values[i][j] + ",");
            }
            System.out.println("");
        }
    }

    private boolean equalsMatrix(Matrix matrixOne, Matrix matrixTwo) {
        if (matrixOne.rows != matrixTwo.rows) return false;
        if (matrixOne.columns != matrixTwo.columns) return false;
        for (int i = 0; i < matrixOne.rows; i++) {
            for (int j = 0; j < matrixOne.columns; j++) {
                if (matrixOne.values[i][j] != matrixTwo.values[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
