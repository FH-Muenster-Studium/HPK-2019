#include <jni.h>
#include "Function.h"
#include "JavaFunction.h"
#include "Differentiator.h"
#include "include/de_hpk_Differentiator.h"

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     de_lab4inf_wrb_Differentiator
 * Method:    differentiate
 * Signature: (Lde/lab4inf/wrb/Function;DDD)D
 */
JNIEXPORT jdouble JNICALL Java_de_hpk_Differentiator_differentiate
        (JNIEnv* env, jobject obj, jobject fct, jdouble x, jdouble eps) {
    double dF = 0;
    try {
        JavaFunction f = JavaFunction(env, fct);
        dF = differentiate(f, x, eps);
    } catch (NoConvergenceException& exception) {
        jclass jExcepClazz = env->FindClass("java/lang/ArithmeticException");
        env->ThrowNew(jExcepClazz, exception.what());
    }
    return (dF);
}

#ifdef __cplusplus
}
#endif
