package de.hpk.matrix;

public class Matrix {

    public final int rows;

    public final int columns;

    public double[][] values;

    public Matrix(int rows, int columns) {
        if (rows < 0 || columns < 0) throw new IllegalArgumentException("Rows and columns needs to be positive.");
        this.rows = rows;
        this.columns = columns;
        this.values = new double[rows][columns];
    }

    public Matrix(Matrix matrix) {
        this.rows = matrix.rows;
        this.columns = matrix.columns;
        this.values = matrix.values;
    }

    public Matrix transpose() {
        if (rows == 0 || columns == 0) return new Matrix(this);
        Matrix transposed = new Matrix(columns, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                transposed.values[j][i] = values[i][j];
        return transposed;
    }


    public Matrix multiply(Matrix matrix) {
        if (this.columns != matrix.rows) {
            throw new IllegalArgumentException("Columns of matrix aren't equal to rows of matrix to multiply with");
        }
        Matrix matrixResult = new Matrix(this.rows, matrix.columns);
        int i, j, k, lengthI, lengthJ, lengthK;
        for (i = 0, lengthI = this.rows; i < lengthI; i++) {
            for (j = 0, lengthJ = matrix.columns; j < lengthJ; j++) {
                for (k = 0, lengthK = this.columns; k < lengthK; k++) {
                    matrixResult.values[i][j] += this.values[i][k] * matrix.values[k][j];
                }
            }
        }
        /*for (int i = 0; i < this.rows; i++) {
            for (int k = 0; k < this.columns; k++) {
                for (int j = 0; j < this.columns; j++) {matrixRight.rows
                    matrixResult.values[i][k] += this.values[i][j] * matrix.values[j][k];
                }
            }
        }*/
        return matrixResult;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Matrix)) {
            return false;
        }
        Matrix other = (Matrix) obj;
        if (this.rows != other.rows) return false;
        if (this.columns != other.columns) return false;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (this.values[i][j] != other.values[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i < this.rows;i++){
            for (int j = 0;j < this.columns;j++){
                stringBuilder.append(values[i][j]);
                if (j + 1 != this.columns) {
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
