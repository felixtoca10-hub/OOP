#include <iostream>
using namespace std;

void intercambiar(int* a, int* b) {
    int temp = *a; // Guarda el valor al que apunta a.
    *a = *b; // Copia en a el valor al que apunta b.
    *b = temp; // Copia el valor temporal en b.
}

int main() {
    int x = 3;
    int y = 9;

    intercambiar(&x, &y); // Se envian las direcciones.

    cout << "x = " << x << ", y = " << y << endl;
    return 0;
}
