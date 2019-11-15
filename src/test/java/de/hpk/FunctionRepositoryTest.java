package de.hpk;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class FunctionRepositoryTest {

    private FunctionRepository functionRepository;

    private static final Random random = new Random();

    private static final double EPS = 1.E-8;

    @Before
    public void setUp() {
        functionRepository = new FunctionRepositoryImpl();
    }

    @Test
    public void testSetGet() {
        double functionReturnValue = random.nextDouble();
        String functionName = UUID.randomUUID().toString();
        functionRepository.setFunction(functionName, args -> {
            return functionReturnValue;
        });
        Assert.assertEquals(functionRepository.getFunction(functionName).eval(), functionReturnValue, EPS);
        Assert.assertTrue(functionRepository.getFunctionNames().contains(functionName));
        Assert.assertNull(functionRepository.getParent());
    }
}
