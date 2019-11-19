package de.hpk.matrix;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MatrixAlgorithm2 implements IMatrixAlgorithm {

    private final Executor threadPoolExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Matrix multiply(Matrix a, Matrix b) {
        Matrix c = new Matrix(a.rows, b.columns);
        Semaphore semaphore = new Semaphore(0, false);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0;j < b.columns;j++) {
                threadPoolExecutor.execute(new MatrixWorker2(i, j, a, b, c, semaphore));
            }
        }
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0;j < b.columns;j++) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException exc) {
                    System.out.println(exc.toString());
                }
            }
        }
        return c;
    }

    @Override
    public String getName() {
        return "Algorithm2";
    }
}
