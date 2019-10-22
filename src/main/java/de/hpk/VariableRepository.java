package de.hpk;

public interface VariableRepository {
    double GetVariable(String name);

    void SetVariable(String name, Double value);
}
