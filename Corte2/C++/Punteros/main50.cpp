#include <iostream>
using namespace std;

// ------------------------------------------------------------
// duplica el valor de la variable apuntada por p
// ------------------------------------------------------------
void duplicar(int* p) {
    // Verificamos que el puntero no sea nulo antes de usarlo.
    if (p != nullptr) {
        *p = (*p) * 2;   // Accedemos al dato y lo duplicamos.
    }
}

// ------------------------------------------------------------
// intercambia los valores de dos variables usando punteros
// ------------------------------------------------------------
void intercambiar(int* a, int* b) {
    // Si alguno es nulo, no hacemos nada.
    if (a == nullptr || b == nullptr) {
        return;
    }

    int temp = *a;   // Guardamos el valor de a.
    *a = *b;         // Copiamos el valor de b en a.
    *b = temp;       // Restauramos el temporal en b.
}

// ------------------------------------------------------------
// muestra un vector usando aritmética de punteros
// ------------------------------------------------------------
void mostrarVector(int* p, int n) {
    if (p == nullptr) {
        cout << "El puntero del vector es nulo." << endl;
        return;
    }

    cout << "Contenido del vector: ";
    for (int i = 0; i < n; i++) {
        cout << *(p + i) << " ";
    }
    cout << endl;
}

// ------------------------------------------------------------
// suma 1 a todos los elementos del vector
// ------------------------------------------------------------
void incrementarVector(int* p, int n) {
    if (p == nullptr) {
        return;
    }

    for (int i = 0; i < n; i++) {
        *(p + i) = *(p + i) + 1;
    }
}

// ------------------------------------------------------------
// muestra información básica de una variable y su puntero
// ------------------------------------------------------------
void mostrarInfoVariable(int* p, const char* nombre) {
    if (p == nullptr) {
        cout << "El puntero de " << nombre << " es nulo." << endl;
        return;
    }

    cout << "Variable: " << nombre << endl;
    cout << "Direccion guardada en el puntero: " << p << endl;
    cout << "Valor almacenado en esa direccion: " << *p << endl;
    cout << "-----------------------------" << endl;
}

// ------------------------------------------------------------
// programa principal
// ------------------------------------------------------------
int main() {
    // Variables normales en memoria automática.
    int x = 10;
    int y = 20;

    // Vector normal.
    int v[3] = {5, 6, 7};

    // px guarda la dirección de x.
    int* px = &x;

    // pv apunta al primer elemento del vector.
    // En muchos contextos, el nombre del arreglo actúa como
    // puntero al primer elemento.
    int* pv = v;

    int opcion;

    do {
        cout << "\n========== MENU CORTO DE PUNTEROS ==========" << endl;
        cout << "1. Mostrar x, &x, px y *px" << endl;
        cout << "2. Duplicar x usando el puntero px" << endl;
        cout << "3. Intercambiar x y y usando punteros" << endl;
        cout << "4. Mostrar vector con punteros" << endl;
        cout << "5. Incrementar todos los elementos del vector" << endl;
        cout << "6. Mostrar informacion detallada de x y y" << endl;
        cout << "0. Salir" << endl;
        cout << "Seleccione una opcion: ";
        cin >> opcion;

        cout << endl;

        switch (opcion) {
            case 1:
                cout << "x = " << x << endl;
                cout << "&x = " << &x << endl;
                cout << "px = " << px << endl;
                cout << "*px = " << *px << endl;
                break;

            case 2:
                cout << "Antes de duplicar, x = " << x << endl;
                duplicar(px);
                cout << "Despues de duplicar, x = " << x << endl;
                break;

            case 3:
                cout << "Antes del intercambio: x = " << x << ", y = " << y << endl;
                intercambiar(&x, &y);
                cout << "Despues del intercambio: x = " << x << ", y = " << y << endl;
                break;

            case 4:
                mostrarVector(pv, 3);
                break;

            case 5:
                cout << "Vector antes de incrementar: ";
                mostrarVector(pv, 3);

                incrementarVector(pv, 3);

                cout << "Vector despues de incrementar: ";
                mostrarVector(pv, 3);
                break;

            case 6:
                mostrarInfoVariable(&x, "x");
                mostrarInfoVariable(&y, "y");
                break;

            case 0:
                cout << "Fin del programa." << endl;
                break;

            default:
                cout << "Opcion no valida." << endl;
                break;
        }

    } while (opcion != 0);

    return 0;
}