cmake_minimum_required(VERSION 3.12)
project (native)
project (native_tests)
#find_package(Java REQUIRED)

#find_package(JNI REQUIRED)

#include(UseJava)
SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -I$ENV{JAVA_HOME}/include -I$ENV{JAVA_HOME}/include/darwin")
set(CMAKE_CXX_STANDARD 17)
#set(CMAKE_JAVA_COMPILE_FLAGS "-source" "1.8" "-target" "1.8")
include_directories(${JNI_INCLUDE_DIRS})
add_library(native Differentiator.cpp Function.h NoConvergenceException.cpp NoConvergenceException.h Integrator.cpp Integrator.h JavaFunction.cpp JavaFunction.h de_hpk_Integrator.cpp de_hpk_Differentiator.cpp Differentiator.h de_hpk_Differentiator.cpp CUnit.h CUnit.c Tests.cpp)
set_target_properties(native PROPERTIES OUTPUT_NAME "hpkNative")
target_link_libraries(native ${JNI_LIBRARIES})

add_executable(native_tests Differentiator.cpp Function.h NoConvergenceException.cpp NoConvergenceException.h Integrator.cpp Integrator.h JavaFunction.cpp JavaFunction.h de_hpk_Integrator.cpp de_hpk_Differentiator.cpp Differentiator.h de_hpk_Differentiator.cpp CUnit.h CUnit.c Tests.cpp)
target_link_libraries(native_tests ${JNI_LIBRARIES})