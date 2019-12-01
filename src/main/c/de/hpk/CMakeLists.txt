cmake_minimum_required(VERSION 2.8.9)
project (native)
add_executable(native differentiator.cpp function.hpp NoConvergenceException.cpp NoConvergenceException.h integrator.cpp integrator.h)