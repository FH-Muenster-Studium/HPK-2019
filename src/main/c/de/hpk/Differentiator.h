//
// Created by Fabian Terhorst on 02.12.19.
//

#ifndef NATIVE_DIFFERENTIATOR_H
#define NATIVE_DIFFERENTIATOR_H

#include "Function.h"
#include "NoConvergenceException.h"
#include <math.h>
#include "include/de_hpk_Integrator.h"
#include <jni.h>
#ifdef __cplusplus
extern "C" {
#endif
/**
 * Numerical differentiate the given function at point x.
 * @param f function to differentiate
 * @param x argument
 * @return value f'(x)
 */
double differentiate(Function& f, double x, double eps);

#ifdef __cplusplus
}
#endif


#endif //NATIVE_DIFFERENTIATOR_H
