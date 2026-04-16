#include <iostream>
using namespace std;

int main() {
	int n = 4;							// Cantidad de elementos deseada.
	int* v = new int[n];           		// Reserva un arreglo dinamico.
    
    for (int i = 0; i < n; i++) {
		v[i] = (i + 1) * 10;			// Llenado del arreglo.
	}

    for (int i = 0; i < n; i++) {
		cout << v[i] << " ";			// Impresion de los datos.
    }
    cout << endl;

    delete[] v;							// Libera el arreglo completo.
    v = nullptr;						// Buena practica.

    return 0;
}