#include <iostream>
using namespace std;

int main() {
    int* p = new int;           // Reserva espacio para un entero en memoria dinamica.
    
    *p = 55;                    // Escribe 55 en la memoria reservada.
    cout << *p << endl;         // Lee el valor guardado.

    delete p;                   // Libera la memoria reservada.
    p = nullptr;                // Buena practica: evitar puntero colgante.

    return 0;
}
