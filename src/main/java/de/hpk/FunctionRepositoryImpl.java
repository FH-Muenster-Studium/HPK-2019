package de.hpk;

import de.lab4inf.wrb.Function;

import java.util.HashMap;
import java.util.Set;

public class FunctionRepositoryImpl implements FunctionRepository {

    private final HashMap<String, Function> functions = new HashMap<>();

    public FunctionRepositoryImpl() {
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.sin(args[0]);
        }, "sin");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.cos(args[0]);
        }, "cos");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.tan(args[0]);
        }, "tan");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.sinh(args[0]);
        }, "sinh");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.cosh(args[0]);
        }, "cosh");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.tanh(args[0]);
        }, "tanh");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.asin(args[0]);
        }, "asin");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.acos(args[0]);
        }, "acos");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.atan(args[0]);
        }, "atan");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.log(args[0]) / Math.log(2);
        }, "ld", "lb", "log2");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.log(args[0]);
        }, "ln");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.log10(args[0]);
        }, "log10", "log");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.log(args[0]) / Math.log(Math.E);
        }, "logE");
        setFunction(args -> {
            FunctionUtils.checkFunctionCountMin(args.length, 1);
            double minValue = -1;
            boolean initialized = false;
            for (int i = 0, length = args.length; i < length; i++) {
                double currValue = args[i];
                if (!initialized || minValue > currValue) {
                    initialized = true;
                    minValue = currValue;
                }
            }
            return minValue;
        }, "min");
        setFunction(args -> {
            FunctionUtils.checkFunctionCountMin(args.length, 1);
            double maxValue = -1;
            boolean initialized = false;
            for (int i = 0, length = args.length; i < length; i++) {
                double currValue = args[i];
                if (!initialized || maxValue < currValue) {
                    initialized = true;
                    maxValue = currValue;
                }
            }
            return maxValue;
        }, "max");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.abs(args[0]);
        }, "abs");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.sqrt(args[0]);
        }, "sqrt");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 2);
            return Math.pow(args[0], args[1]);
        }, "pow");
        setFunction(args -> {
            FunctionUtils.checkFunctionCount(args.length, 1);
            return Math.exp(args[0]);
        }, "exp");
    }

    private void setFunction(Function function, String... names) {
        for (int i = 0, length = names.length; i < length; i++) {
            functions.put(names[i], function);
        }
    }

    @Override
    public Function getFunction(String name) {
        if (!functions.containsKey(name)) {
            throw new IllegalArgumentException("Function with name: " + name + " is undefined!");
        }
        return functions.get(name);
    }

    @Override
    public void setFunction(String name, Function value) {
        /*if (functions.containsKey(name)) {
            throw new IllegalArgumentException("Function with name: " + name + " already defined!");
        }*/
        functions.put(name, value);
    }

    @Override
    public Set<String> getFunctionNames() {
        return functions.keySet();
    }

    @Override
    public FunctionRepository getParent() {
        return null;
    }
}
