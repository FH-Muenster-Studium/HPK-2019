//
// Created by Fabian Terhorst on 02.12.19.
//

#ifndef NATIVE_JAVAFUNCTION_H
#define NATIVE_JAVAFUNCTION_H

#include <jni.h>

#include <jni.h>
#include "Function.h"

/**
 * A C++ Function suitable for JNI calls.
 */
class JavaFunction : public Function {
private:
    JNIEnv* env;       // JVM environment
    jobject instance;  // the Java function instance
    jmethodID fct;       // the Java method
    jstring jname;     // the Java function name
    jdoubleArray array;  // the Java array as argument
public:
    /**
     * Constructor to wrap a Java Function implementation.
     */
    JavaFunction(JNIEnv* env, jobject instance);

    ~JavaFunction() override;

    // overloaded operator to execute  double y = f(x)
    // for java functions implementations.
    double operator()(double x) const override {
        // our C++ functions are one dimensional the java function not...
        env->SetDoubleArrayRegion(array, 0, 1, &x);
        return (env->CallDoubleMethod(instance, fct, array));
    }
};


#endif //NATIVE_JAVAFUNCTION_H
