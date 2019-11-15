package de.hpk;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class VariableRepositoryTest {

    private VariableRepository variableRepository;

    private static final Random random = new Random();

    private static final double EPS = 1.E-8;

    @Before
    public void setUp() {
        variableRepository = new VariableRepositoryImpl();
    }

    @Test
    public void testSetGet() {
        double variable = random.nextDouble();
        String variableName = UUID.randomUUID().toString();
        variableRepository.setVariable(variableName, variable);
        Assert.assertEquals(variableRepository.getVariable(variableName), variable, EPS);
        Assert.assertTrue(variableRepository.getVariableNames().contains(variableName));
        Assert.assertNull(variableRepository.getParent());
    }

    @Test
    public void testParent() {
        double parentVariable = random.nextDouble();
        String parentVariableName = UUID.randomUUID().toString();
        variableRepository.setVariable(parentVariableName, parentVariable);

        VariableRepository child = new VariableRepositoryImpl(variableRepository);
        double variable = random.nextDouble();
        String variableName = UUID.randomUUID().toString();
        child.setVariable(variableName, variable);
        Assert.assertEquals(child.getVariable(variableName), variable, EPS);
        Assert.assertTrue(child.getVariableNames().contains(variableName));
        Assert.assertTrue(child.getVariableNames().contains(parentVariableName));
        Assert.assertEquals(child.getParent(), variableRepository);
    }
}
