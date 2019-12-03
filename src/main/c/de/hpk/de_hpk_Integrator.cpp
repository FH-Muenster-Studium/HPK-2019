#include "Integrator.h"
#include "include/de_hpk_Integrator.h"
#include "NoConvergenceException.h"

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     de_lab4inf_wrb_Integrator
 * Method:    integrate
 * Signature: (Lde/lab4inf/wrb/Function;DDD)D
 */
JNIEXPORT jdouble JNICALL Java_de_hpk_Integrator_integrate
        (JNIEnv* env, jobject obj, jobject fct, jdouble a, jdouble b, jdouble eps) {
    double dF = 0;
    try {
        JavaFunction f = JavaFunction(env, fct);
        dF = integrate(f, a, b, eps);
    } catch (NoConvergenceException& exception) {
        jclass jExcepClazz = env->FindClass("java/lang/ArithmeticException");
        env->ThrowNew(jExcepClazz, exception.what());
    }
    return (dF);
}

#ifdef __cplusplus
}
#endif
