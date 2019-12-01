//
// Created by Fabian Terhorst on 01.12.19.
//

#ifndef NATIVE_NOCONVERGENCEEXCEPTION_H
#define NATIVE_NOCONVERGENCEEXCEPTION_H

#include <exception>

class NoConvergenceException : std::exception {
public:
    const char* what() const throw();
};

#endif //NATIVE_NOCONVERGENCEEXCEPTION_H
