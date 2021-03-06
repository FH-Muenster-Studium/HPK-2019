#include "Integrator.h"
#include <math.h>
#include "Function.h"
#include "NoConvergenceException.h"

#define ABSOLUTE(i) ((i) > 0 ? (i) : -(i))

#ifdef __cplusplus
extern "C" {
#endif

double integrate(Function &f, double a, double b, double eps) {
    if (a == b)
        return 0.0;
    double last_result = 0, result, lasteps, tempAbsoluteEps = 0;//tempAbsoluteEps erst im zweiten durchlauf geprüft
    int N = 126, reps = 4;
    double fa = f(a), fb = f(b);
    bool firstrun = true;


    double sum1 = 0.0, sum2 = 0.0;
    double h = (b - a) / N;

    result = fa + fb;
    for (int j = 1; j <= N / 2 - 1; j++) {
        sum1 += 2 * f(a + j * h * 2);
    }
    for (int j = 1; j <= N / 2; j++) {
        sum2 += 2 * f(a + (2 * j - 1) * h);
    }
    result += sum1 + sum2 * 2;
    result *= h / 3;

    N *= 2;


    do {
        lasteps = tempAbsoluteEps;
        last_result = result;

        h = (b - a) / N;
        result = fa + fb;
        sum1 += sum2;
        sum2 = 0.0;
        for (int j = 1; j <= N / 2; j++) {
            sum2 += 2 * f(a + (2 * j - 1) * h);
        }
        result += sum1 + sum2 * 2;
        result *= h / 3;

        N *= 2;
        tempAbsoluteEps = ABSOLUTE(result - last_result);

        if (!firstrun && tempAbsoluteEps > lasteps)
            throw NoConvergenceException();    //im ersten durchlauf noch kein lasteps
        firstrun = false;
    } while (tempAbsoluteEps > eps && (ABSOLUTE(result / last_result - 1.0)) > eps && reps--);

    if (reps == -1) throw NoConvergenceException();
    if (isnan(tempAbsoluteEps)) throw NoConvergenceException();
    return result;
}
#ifdef __cplusplus
}
#endif
