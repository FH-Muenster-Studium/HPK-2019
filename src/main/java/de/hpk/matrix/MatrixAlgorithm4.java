package de.hpk.matrix;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MatrixAlgorithm4 implements IMatrixAlgorithm {

    private final Executor threadPoolExecutor = Executors.newFixedThreadPool(8);

    public Matrix multiply(Matrix a, Matrix b) {
        Matrix r = b.transpose();
        Matrix c = new Matrix(a.rows, b.columns);
        Semaphore semaphore = new Semaphore(0, false);
        for (int i = 0; i < a.rows; i++) {
            threadPoolExecutor.execute(new MatrixWorker4(i, a, b, r, c, semaphore));
        }
        for (int i = 0; i < a.rows; i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException exc) {
                System.out.println(exc.toString());
            }
        }
        return c;
    }

    @Override
    public String getName() {
        return "Algorithm4";
    }
}
