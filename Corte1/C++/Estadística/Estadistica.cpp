#include <iostream>
using namespace std;
// Métodos estáticos = los llamas usando ClassName::method(...)
class Estadistica {
public:
    static int suma(const int a[], int n) {
        int total = 0;
        for (int i = 0; i < n; i++) total += a[i];
        return total;
    }
    static double promedio(const int a[], int n) {
        return (double)suma(a, n) / n;
    }
};
int main() {
    // Matriz de tamaño fijo (Unidad 1: simple)
    int grades[] = {80, 75, 90, 60, 95};
    int n = sizeof(grades) / sizeof(grades[0]);
    cout << "Suma: " << Estadistica::suma(grades, n) << "\n";
    cout << "Promedio: " << Estadistica::promedio(grades, n) << "\n";
    return 0;
}