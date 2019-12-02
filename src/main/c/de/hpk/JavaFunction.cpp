#include "JavaFunction.h"

/*

JNIEnv   *env;       // JVM environment
jobject   instance;  // the Java function instance
jmethodID fct;       // the Java method
jstring   jname;     // the Java function name
jdoubleArray array;  // the Java array as argument
*/

JavaFunction::JavaFunction(JNIEnv* env, jobject instance) {
    this->env = env;
    this->instance = instance;
    jclass clazz = env->GetObjectClass(instance);
    this->fct = env->GetMethodID(clazz, "eval", "([D)D");
    this->array = env->NewDoubleArray(1);
}


JavaFunction::~JavaFunction() = default;
