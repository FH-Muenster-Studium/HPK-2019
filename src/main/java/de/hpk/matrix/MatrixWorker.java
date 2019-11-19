package de.hpk.matrix;

import java.util.concurrent.Semaphore;

public class MatrixWorker implements Runnable {

    private final int row;

    private final Matrix result;

    private final Matrix left, right, r;

    private final Semaphore semaphore;

    public MatrixWorker(int row, Matrix left, Matrix right, Matrix r, Matrix result, Semaphore semaphore) {
        this.row = row;
        this.left = left;
        this.right = right;
        this.r = r;
        this.result = result;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int j = 0; j < right.columns; j++) {
            result.values[row][j] = Vector.multiply(left.values[row], r.values[j]);
        }
        this.semaphore.release();
    }
}