#ifndef NATIVE_INTEGRATOR_H
#define NATIVE_INTEGRATOR_H

#include "Function.h"
#include <jni.h>
#include "JavaFunction.h"

#ifdef __cplusplus
extern "C" {
#endif
/**                                   /b
 * Numerical calculation of F[b,a] = / f(t) dt within integration borders a,b.
 *                                  /a
 * @param f function to integrate
 * @param a left integration border
 * @param x right integration border
 * @param eps precission
 * @return F(b)-F(a)
 */
double integrate(Function &f, double a, double b, double eps);
#ifdef __cplusplus
}
#endif


#endif //NATIVE_INTEGRATOR_H
