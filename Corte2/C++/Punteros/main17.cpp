#include <iostream>
using namespace std;

int main() {
    int x = 25;  // x guarda el valor 25.
    int* p = &x;  // p guarda la direccion de x.

    cout << "x = " << x << endl;  // Imprime el valor de x.
    cout << "&x = " << &x << endl;  // Imprime la direccion de x.
    cout << "p = " << p << endl;    // Imprime lo que guarda p: la direccion.
    cout << "*p = " << *p << endl;  // Imprime el valor al que p apunta.

    return 0;   // Fin del programa.
}