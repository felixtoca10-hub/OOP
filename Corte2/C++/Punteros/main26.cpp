#include <iostream>
using namespace std;

void duplicar(int* p) {
    if (p != nullptr) {     // Verificacion de seguridad.
        *p = (*p) * 2;      // Modifica el dato original.
    }
}

int main() {
    int numero =7;          // Variable original.
    duplicar (&numero);     // Enviamos la direccion de numero. 

    cout << numero << endl;   // Imprime 14.
    return 0;
}