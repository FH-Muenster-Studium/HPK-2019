package de.hpk.matrix;

import java.util.concurrent.Semaphore;

public class MatrixWorker2 implements Runnable {

    private final int i, j;

    private final Matrix result;

    private final Matrix left, right;

    private final Semaphore semaphore;

    public MatrixWorker2(int i, int j, Matrix left, Matrix right, Matrix result, Semaphore semaphore) {
        this.i = i;
        this.j = j;
        this.left = left;
        this.right = right;
        this.result = result;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int k = 0; k < left.columns; k++) {
            result.values[i][j] += left.values[i][k] * right.values[k][j];
        }
        this.semaphore.release();
    }
}