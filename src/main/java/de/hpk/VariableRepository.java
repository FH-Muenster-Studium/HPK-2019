package de.hpk;

import java.util.Set;

public interface VariableRepository {
    double getVariable(String name);

    void setVariable(String name, Double value);

    Set<String> getVariableNames();
}
