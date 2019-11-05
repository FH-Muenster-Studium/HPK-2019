package de.hpk;

import java.util.Set;

public interface VariableRepository {
    double getVariable(String name);

    void setVariable(String name, Double value);

    Set<String> getVariableNames();

    VariableRepository getParent();

    default VariableRepository concat(VariableRepository variableRepository) {
        for (String name : getVariableNames()) {
            variableRepository.setVariable(name, getVariable(name));
        }
        return variableRepository;
    }
}
