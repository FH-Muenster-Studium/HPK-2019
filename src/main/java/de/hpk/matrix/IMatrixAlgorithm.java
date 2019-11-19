package de.hpk.matrix;

public interface IMatrixAlgorithm {
    Matrix multiply(Matrix a, Matrix b);

    String getName();
}
