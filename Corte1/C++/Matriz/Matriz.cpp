#include <iostream>
using namespace std;

class Matriz2x2 {
public:
    int data[2][2];

    void cargarEjemplo() {
        data[0][0] = 1; data[0][1] = 2;
        data[1][0] = 3; data[1][1] = 4;
    }

    void imprimir() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                cout << data[i][j] << " ";
            }
            cout << "\n";
        }
    }

    int sumaElementos() {
        int s = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                s += data[i][j];
            }
        }
        return s;
    }
};

int main() {
    Matriz2x2 m;
    m.cargarEjemplo();
    m.imprimir();
    cout << "Suma = " << m.sumaElementos() << "\n";
    return 0;
}
