#include <iostream>
using namespace std;

int main() {
    int x =25;   // Variable original.
    int* p = &x;   // p apunta a x.

    *p = 80;      // Va a la direccion de x y cambia su contenido.

    cout << "x = " << x << endl;   // Ahora imprime 80.
    cout << "*p = " << *p << endl;  // Tambien imprime 80.

    return 0;
}