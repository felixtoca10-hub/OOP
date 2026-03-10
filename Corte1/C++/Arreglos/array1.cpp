#include <iostream>
#include <string>

using namespace std;

// Ejemplo para primera clase: arreglos (array) en C++
// - Usamos un tamaño máximo fijo (MAX) porque el array NO es dinámico.
// - El usuario decide n, pero n debe ser <= MAX.
class Arrays1 {
public:
    static const int MAX = 100; // tamaño máximo permitido
    int n;                      // cantidad real de elementos a usar
    int data[MAX];              // array fijo (NO dinámico)

    // Constructor: recibe n y lo guarda
    Arrays1(int n) {
        this->n = n;
        // (Opcional) inicializar con ceros
        for (int i = 0; i < n; i++) {
            data[i] = 0;
        }
    }

    // Lee valores por teclado y los guarda en el array
    void setVector(const string& name) {
        for (int i = 0; i < n; i++) {
            cout << name << "[" << i << "]: ";
            cin >> data[i];
        }
    }

    // Imprime el array en formato: name = [ a , b , c ]
    void getVector(const string& name) const {
        cout << name << " = [ ";
        for (int i = 0; i < n; i++) {
            cout << data[i];
            if (i < n - 1) cout << " , ";
        }
        cout << " ]" << endl;
    }
};

int main() {
    int n;
    cout << "Tamano n (max " << Arrays1::MAX << "): ";
    cin >> n;

    // Validación para no salirnos del tamaño máximo del array
    if (n < 1 || n > Arrays1::MAX) {
        cout << "Error: n debe estar entre 1 y " << Arrays1::MAX << ".\n";
        return 0;
    }

    Arrays1 v(n);
    v.setVector("myVector");
    v.getVector("myVector");

    return 0;
}