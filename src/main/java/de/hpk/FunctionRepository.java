package de.hpk;

import de.lab4inf.wrb.Function;

import java.util.Set;

public interface FunctionRepository {
    Function getFunction(String name);

    void setFunction(String name, Function value);

    Set<String> getFunctionNames();

    FunctionRepository getParent();

    default FunctionRepository concat(FunctionRepository functionRepository) {
        for (String name : getFunctionNames()) {
            functionRepository.setFunction(name, getFunction(name));
        }
        return functionRepository;
    }
}
