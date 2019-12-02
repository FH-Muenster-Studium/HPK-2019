package de.hpk;

import de.lab4inf.wrb.WRBScript;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DifferentiatorTest {

    private double eps = 1e-8;
    private WRBScript script;
    private Differentiator differentiator;
    private int count;

    @Before
    public final void differentiatorTestSetUp() {
        script = new WRBScript();
        differentiator = new Differentiator();
        count = 0;
    }

    @After
    public final void DifferentiatorTestTearDown() {
        System.out.println(count);
    }

    private double MySin(double x) {
        count++;
        return Math.sin(x);
    }

    @Test
    public final void testSinus() {
        double x = Math.random();

        double result = differentiator.differentiate(args -> MySin(args[0]), x, eps);

        Assert.assertEquals(script.parse("cos(" + x + ")"), result, eps);
    }

    private double Poly(String fkt, double x) {
        return script.parse(fkt + "(" + x + ")");
    }


    @Test
    public final void testPoly() {
        double x = Math.random();
        script.parse("f(x)=x^8+         4*x^4+      2*x^2+   5");
        script.parse("f2(x)=8*x^7+      16*x^3+     4*x");
        script.parse("f3(x)=8*7*x^6+    16*3*x^2+   4");
        script.parse("f4(x)=8*7*6*x^5+  16*3*2*x");

        double result = differentiator.differentiate(script.getFunction("f"), x);
        double expected = script.parse("f2(" + x + ")");
        Assert.assertEquals(result, expected, eps);

        result = differentiator.differentiate(script.getFunction("f2"), x);
        expected = script.parse("f3(" + x + ")");
        Assert.assertEquals(result, expected, eps);

        result = differentiator.differentiate(script.getFunction("f3"), x);
        expected = script.parse("f4(" + x + ")");
        Assert.assertEquals(result, expected, eps);
    }

}
