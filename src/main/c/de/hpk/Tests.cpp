#include "CUnit.h"
#include "Integrator.h"
#include "CUnit.h"
#include "Differentiator.h"
#include <stdlib.h>
#include <math.h>
#include <time.h>

#define EPS (1.E-8)
#define EPS1 (1.E-9)

double Linear(double x) {
    return x;
}

//5x³-3x²-x+9
double Polynominal(double x) {
    return 5 * x * x * x + (-3) * x * x + (-x) + 9;
}

//integral: 5/4x^4 -x³ -x²/2 +9x +c
double PolynominalIntegral(double x) {
    return 5.0 / 4.0 * x * x * x * x - (x * x * x) - (x * x / 2.0) + 9.0 * x;
}

//Derivative: -1 - 6 x + 15 x^2
double PolynominalDerivative(double x) {
    return 15 * x * x + -6 * x - 1;
}

double xcosx(double x) {
    return x * cos(x);
}

double expXsinX(double x) {
    return exp(x) * sin(x);
}

double Exponential(double x) {
    return x * x;
}

double noconvergence(double x) {
    return 1 / (x);
}

extern int Integrate_LinearTest(int, char**) {
    double a = 0, b = rand() % 1000;
    Function function = Function(Linear, "Linear");
    double result = integrate(function, a, b, EPS);

    assertEqualsF(result, b * b / 2, EPS)

    return 0;
}

extern int Integrate_Sinus1Test(int, char**) {
    double a = 0, b = 2 * M_PI;
    Function function = Function(sin, "Sin");
    double result = integrate(function, a, b, EPS);

    assertEqualsF(result, 0.0, EPS)

    return 0;
}

extern int Integrate_Sinus2Test(int, char**) {
    int b = rand() % 200, a = b % 100;
    Function function = Function(sin, "Sin");
    double result = integrate(function, a, b, EPS);
    double expected = (cos(a)) - (cos(b));

    assertEqualsF(result, expected, EPS)

    return 0;
}

extern int Integrate_ExpTest(int, char**) {
    int a = 0, b = 1;
    Function function = Function(exp, "Exp");
    double result = integrate(function, a, b, EPS);
    double expected = (M_E) - (1);

    assertEqualsF(result, expected, EPS)

    return 0;
}

/*double Exponential
extern int ExponentialTest(int,char **) {
    int n,a=0,b=1;
    Function function = Function(exp,"Exp");
    double result = integrate(function,a,b,EPS);
    double expected = (M_E)-(1);

    assertEqualsF(result,expected,EPS)

    return 0;
}*/

extern int Integrate_PolynominalTest(int, char**) {
    int a = -4, b = 13;
    Function function = Function(Polynominal, "Exp");
    double result = integrate(function, a, b, EPS);
    double expected = PolynominalIntegral(b) - PolynominalIntegral(a);

    assertEqualsF(result, expected, EPS)

    return 0;
}


extern int Integrate_lnTest(int, char**) {
    double a = 1, b = M_E;
    Function function = Function(log, "ln");
    double result = integrate(function, a, b, EPS);
    double expected = 1.0;

    assertEqualsF(result, expected, EPS)

    return 0;
}


extern int Integrate_xcosxTest(int, char**) {
    double a = 0, b = M_PI;
    Function function = Function(xcosx, "xcosx");
    double result = integrate(function, a, b, EPS);
    double expected = -2.0;

    assertEqualsF(result, expected, EPS)

    return 0;
}


extern int Integrate_expXsinXTest(int, char**) {
    double a = -M_PI, b = M_PI;
    Function function = Function(expXsinX, "expXsinX");
    double result = integrate(function, a, b, EPS);
    double expected = sinh(M_PI); //Mathe Übungen

    assertEqualsF(result, expected, EPS)

    return 0;
}


extern int Integrate_noconvergenceTest(int, char**) {
    double a = -0.5, b = 0.5;
    Function function = Function(noconvergence, "noconvergence");
    try {
        integrate(function, a, b, EPS);
    } catch (NoConvergenceException& exception) {
        assertEqualsS(exception.what(), "no convergence");
        return 0;
    }
    fail("no exception")
    return 0;
}


extern int Differentiate_ExponentialTest(int, char**) {
    double x = 5;
    Function function = Function(Exponential, "Exponential");
    double result = differentiate(function, x, EPS1);

    assertEqualsF(result, 10.0, EPS);

    return 0;
}


extern int Differentiate_LinearTest(int, char**) {

    srand(static_cast<unsigned int>(time(nullptr)));

    double x = rand() % 1000 - 500;
    Function function = Function(Linear, "Linear");
    double result = differentiate(function, x, EPS1);

    assertEqualsF(result, 1.0, EPS)


    return 0;
}

extern int Differentiate_SinusTest(int, char**) {
    double x = rand() % 100;
    Function function = Function(sin, "Sin");
    double result = differentiate(function, x, EPS1);

    assertEqualsF(result, cos(x), EPS)

    return 0;
}

extern int Differentiate_CosinusTest(int, char**) {
    int x = rand() % 100;
    Function function = Function(cos, "Cos");
    double result = differentiate(function, x, EPS1);

    assertEqualsF(result, -sin(x), EPS)

    return 0;
}

extern int Differentiate_ExpTest(int, char**) {
    int x = rand() % 1000;
    Function function = Function(exp, "Exp");
    double result = differentiate(function, x, EPS);

    assertEqualsF(result, exp(x), EPS)

    return 0;
}

extern int Differentiate_PolynominalTest(int, char**) {
    int x = 4;
    Function function = Function(Polynominal, "Exp");
    double result = differentiate(function, x, EPS1);
    double expected = PolynominalDerivative(x);

    assertEqualsF(result, expected, EPS)

    return 0;
}

extern int Differentiate_lnTest(int, char**) {
    double x = rand() % 100 + 1;//>0
    Function function = Function(log, "ln");
    double result = differentiate(function, x, EPS1);
    double expected = 1 / x;

    assertEqualsF(result, expected, EPS)

    return 0;
}

extern int Differentiate_xcosxTest(int, char**) {
    int x = 14;
    Function function = Function(xcosx, "xcosx");
    double result = differentiate(function, x, EPS1);
    double expected = cos(x) - x * sin(x);

    assertEqualsF(result, expected, EPS)
    return 0;
}

extern int Differentiate_expXsinXTest(int, char**) {
    double x = 3;
    Function function = Function(expXsinX, "expXsinX");
    double result = differentiate(function, x, EPS1);
    double expected = exp(x) * (sin(x) + cos(x));

    assertEqualsF(result, expected, EPS)

    return 0;
}

DECLARE_TEST(Integrate_Linear)

DECLARE_TEST(Integrate_Sinus1)

DECLARE_TEST(Integrate_Sinus2)

DECLARE_TEST(Integrate_Exp)

DECLARE_TEST(Integrate_Polynominal)

DECLARE_TEST(Integrate_ln)

DECLARE_TEST(Integrate_xcosx)

DECLARE_TEST(Integrate_expXsinX)

DECLARE_TEST(Integrate_noconvergence)

DECLARE_TEST(Differentiate_Exponential)

DECLARE_TEST(Differentiate_Linear)

DECLARE_TEST(Differentiate_Sinus)

DECLARE_TEST(Differentiate_Cosinus)

DECLARE_TEST(Differentiate_Exp)

DECLARE_TEST(Differentiate_Polynominal)

DECLARE_TEST(Differentiate_ln)

DECLARE_TEST(Differentiate_xcosx)

DECLARE_TEST(Differentiate_expXsinX)

BEG_SUITE(suite)
                ADD_TEST(Integrate_Linear),
                ADD_TEST(Integrate_Sinus1),
                ADD_TEST(Integrate_Sinus2),
                ADD_TEST(Integrate_Exp),
                ADD_TEST(Integrate_Polynominal),
                ADD_TEST(Integrate_ln),
                ADD_TEST(Integrate_xcosx),
                ADD_TEST(Integrate_expXsinX),
                ADD_TEST(Integrate_noconvergence),

                ADD_TEST(Differentiate_Exponential),
                ADD_TEST(Differentiate_Linear),
                ADD_TEST(Differentiate_Sinus),
                ADD_TEST(Differentiate_Cosinus),
                ADD_TEST(Differentiate_Exp),
                ADD_TEST(Differentiate_Polynominal),
                ADD_TEST(Differentiate_ln),
                ADD_TEST(Differentiate_xcosx),
                ADD_TEST(Differentiate_expXsinX)
END_SUITE(suite)

RUN_SUITE(suite)
