//
// Created by Fabian Terhorst on 01.12.19.
//

#include "NoConvergenceException.h"

const char* NoConvergenceException::what() const throw() {
    return "no convergence";
}
