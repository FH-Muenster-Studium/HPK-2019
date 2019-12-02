package de.hpk;

import de.lab4inf.wrb.Function;
import de.lab4inf.wrb.WRBScript;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntegratorTest {

    private double eps = 1e-8;
    private WRBScript script;
    private Integrator integrator;
    private int count;

    @Before
    public final void integratorTestSetUp() {
        script = new WRBScript();
        integrator = new Integrator();
        count = 0;
    }

    @After
    public final void ntegratorTestTearDown() {
        System.out.println(count);
    }

    @Test
    public final void testSinus() {
        double a = -17, b = 17;

        double result = integrator.integrate(args -> MySin(args[0]), a, b, eps);

        Assert.assertEquals(0.0, result, eps);
    }

    private double MySin(double x) {
        count++;
        return Math.sin(x);
    }

    @Test
    public final void testSinus2() {
        double a = Math.PI * 2, b = Math.PI * 3;
//        double a=0,b=2;

        double result = integrator.integrate(args -> MySin(args[0]), a, b, eps);
        //cos(PI*3)-cos(PI*2)=cos(PI)-cos(0)=
        Assert.assertEquals(2.0, result, eps);
    }

    //5x³-3x²-x+9
    private double Polynominal(double x) {
        count++;
        return 5 * x * x * x + (-3) * x * x + (-x) + 9;
    }

    //integral: 5/4x^4 -x³ -x²/2 +9x +c
    private double PolynominalIntegral(double x) {
        return 5.0 / 4.0 * x * x * x * x - (x * x * x) - (x * x / 2.0) + 9.0 * x;
    }

    @Test
    public final void PolynominalTest() {
        int a = -4, b = 13;
        Function function = args -> Polynominal(args[0]);

        double result = integrator.integrate(function, a, b, eps);
        double expected = PolynominalIntegral(b) - PolynominalIntegral(a);
        Assert.assertEquals(expected, result, eps);
    }


    private double ln(double... args) {
        count++;
        return Math.log(args[0]);
    }

    @Test
    public final void lnTest() {
        double a = 1, b = Math.E;
        double result = integrator.integrate(args -> ln(args[0]), a, b, eps);
        double expected = 1.0;

        Assert.assertEquals(result, expected, eps);
    }


    private double xcosx(double x) {
        count++;
        return x * Math.cos(x);
    }

    @Test
    public final void xcosxTest() {
        double a = 0, b = Math.PI;
        double result = integrator.integrate(args -> xcosx(args[0]), a, b, eps);
        double expected = -2.0;

        Assert.assertEquals(result, expected, eps);
    }

    private double expXsinX(double x) {
        count++;
        return Math.exp(x) * Math.sin(x);
    }

    /*@Test
    public final void expXsinXTest() {
        double a = -Math.PI, b = Math.PI;
        double result = integrator.integrate(args -> expXsinX(args[0]), a, b, eps);
        double expected = Math.sinh(Math.PI);

        Assert.assertEquals(result, expected, eps);
    }*/
}

