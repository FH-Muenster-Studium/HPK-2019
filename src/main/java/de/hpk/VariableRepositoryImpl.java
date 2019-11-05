package de.hpk;

import java.util.HashMap;
import java.util.Set;

public class VariableRepositoryImpl implements VariableRepository {

    private VariableRepository parentVariableRepository;

    public VariableRepositoryImpl() {
        this(null);
    }

    public VariableRepositoryImpl(VariableRepository parentVariableRepository) {
        this.parentVariableRepository = parentVariableRepository;
    }

    private final HashMap<String, Double> variables = new HashMap<>();

    public double getVariable(String name) {
        if (!variables.containsKey(name)) {
            if (parentVariableRepository != null) {
                return parentVariableRepository.getVariable(name);
            }
            throw new IllegalArgumentException("Variable: " + name + " undefined!");
        }
        return variables.get(name);
    }

    public void setVariable(String name, Double value) {
        variables.put(name, value);
    }

    @Override
    public Set<String> getVariableNames() {
        Set<String> variableNames = variables.keySet();
        if (parentVariableRepository != null) {
            variableNames.addAll(parentVariableRepository.getVariableNames());
        }
        return variableNames;
    }

    public VariableRepository getParent() {
        return parentVariableRepository;
    }
}
