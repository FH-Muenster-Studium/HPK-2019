package de.hpk;

import java.util.HashMap;

public class Variables implements VariableRepository {

    private final HashMap<String, Double> variables = new HashMap<>();

    public double GetVariable(String name) {
        if (!variables.containsKey(name)) {
            throw new IllegalArgumentException("Variable: " + name + " undefined!");
        }
        return variables.get(name);
    }

    public void SetVariable(String name, Double value) {
        variables.put(name, value);
    }
}
