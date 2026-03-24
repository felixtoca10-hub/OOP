#ifndef ESTUDIANTE_CPP
#define ESTUDIANTE_CPP
#include <string>

class Estudiante {
private:
    // REQUERIMIENTO 25: Atributos privados (Encapsulamiento)
    std::string codigo, nombre;
    int edad;

public:
    // REQUERIMIENTO 27: Sobrecarga de Constructores
    Estudiante() : codigo(""), nombre(""), edad(0) {} // Por defecto
    
    Estudiante(std::string c, std::string n, int e) // Con parámetros
        : codigo(c), nombre(n), edad(e) {}

    // Getters para acceder a los datos privados
    std::string getCodigo() { return codigo; }
    std::string getNombre() { return nombre; }
};
#endif