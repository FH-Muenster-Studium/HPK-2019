package de.hpk;

import java.util.HashMap;
import java.util.Set;

public class Variables implements VariableRepository {

    private final HashMap<String, Double> variables = new HashMap<>();

    public double getVariable(String name) {
        if (!variables.containsKey(name)) {
            throw new IllegalArgumentException("Variable: " + name + " undefined!");
        }
        return variables.get(name);
    }

    public void setVariable(String name, Double value) {
        variables.put(name, value);
    }

    @Override
    public Set<String> getVariableNames() {
        return variables.keySet();
    }
}
