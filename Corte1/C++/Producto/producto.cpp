#include <iostream>
#include <string>
using namespace std;

class Producto {
public:
    string nombre;
    double precio;

    Producto() {
        nombre = "Sin nombre";
        precio = 0.0;
    }

    Producto(string nombre) {
        this->nombre = nombre;
        this->precio = 0.0;
    }

    Producto(string nombre, double precio) {
        this->nombre = nombre;
        this->precio = precio;
    }

    void mostrar() {
        cout << "Nombre = " << nombre << "\n";
        cout << "Precio = " << precio << "\n";
    }
};

int main() {
    Producto p1;
    Producto p2("Cuaderno");
    Producto p3("Lapiz", 2500.0);

    p1.mostrar();
    cout << "\n";
    p2.mostrar();
    cout << "\n";
    p3.mostrar();

    return 0;
}
