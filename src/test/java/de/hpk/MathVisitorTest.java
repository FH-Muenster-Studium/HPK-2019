package de.hpk;

import org.junit.Assert;
import org.junit.Test;

public class MathVisitorTest {
    @Test
    public void constructorTest() {
        VariableRepository variableRepository = new VariableRepositoryImpl();
        FunctionRepository functionRepository = new FunctionRepositoryImpl();
        MathVisitor mathVisitor = new MathVisitor(variableRepository, functionRepository);
        Assert.assertEquals(variableRepository, mathVisitor.getVariableRepository());
        Assert.assertEquals(functionRepository, mathVisitor.getFunctionRepository());
    }

    @Test
    public void defaultConstructorTest() {
        MathVisitor mathVisitor = new MathVisitor();
        Assert.assertNotNull(mathVisitor.getVariableRepository());
        Assert.assertNotNull(mathVisitor.getFunctionRepository());
    }
}
