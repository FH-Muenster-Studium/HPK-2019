#include "Differentiator.h"

#include <iostream>

#define ABSOLUTE(i) ((i) > 0 ? (i) : -(i))

#ifdef __cplusplus
extern "C" {
#endif

double delta(Function &f, double x, double h) {
    return f(x + h) - f(x - h);
}

double differentiate(Function &f, double x, double eps) {
    double last_result, result = 0, AbsoluteFault = 0, LastAbsoluteFault = 0;
    int reps = 6;

    double h = 1e-5;

    result = (8 * delta(f, x, h) - delta(f, x, 2 * h)) / (12 * h);

    h /= 2;
    do {
        last_result = result;

        result = (8 * delta(f, x, h) - delta(f, x, 2 * h)) / (12 * h);

        result = (16 * result - last_result) / 15;
        h /= 2;
        AbsoluteFault = ABSOLUTE(result - last_result);

    } while (AbsoluteFault > eps && (ABSOLUTE(result / last_result - 1.0)) > eps && reps--);

    if (reps == -1) throw NoConvergenceException();
    return result;
}

#ifdef __cplusplus
}

#endif
