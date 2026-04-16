#include <iostream>
using namespace std;

int main() {
    int v[4] = {10, 20, 30, 40};        // Arreglo de 4 enteros.

    cout << v[0] << endl;               // Acceso clasico por indice.
    cout << *v << endl;                 // Acceso al primer elemento por puntero.
    cout << *(v + 1) << endl;           // Segundo elemento: 20.
    cout << *(v + 2) << endl;           // Tercer elemento: 30.
    
    return 0;
}
