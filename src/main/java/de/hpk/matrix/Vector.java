package de.hpk.matrix;

public class Vector {
    public double[] values;

    public static double multiply(double[] vector1, double[] vector2) {
        int length = vector1.length;
        double result = 0;
        for (int i = 0; i < length; i++) {
            result += vector1[i] * vector2[i];
        }
        return result;
    }
}
