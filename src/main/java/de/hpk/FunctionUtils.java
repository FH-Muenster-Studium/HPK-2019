package de.hpk;

public class FunctionUtils {
    public static void checkFunctionCount(int argsLength, int expectedLength) {
        if (argsLength != expectedLength) {
            throw new IllegalArgumentException("Function parameter count mismatch, expected " + expectedLength + " parameters, but got " + argsLength + ".");
        }
    }

    public static void checkFunctionCountMin(int argsLength, int expectedLength) {
        if (argsLength < expectedLength) {
            throw new IllegalArgumentException("Function parameter count mismatch, expected at least " + expectedLength + " parameters.");
        }
    }
}
