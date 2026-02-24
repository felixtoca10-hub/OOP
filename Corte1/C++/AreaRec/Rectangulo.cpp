#include <iostream>
using namespace std;

class Rectangulo {
public:
    double base;
    double altura;

    Rectangulo(double b, double h){
        base = b;
        altura = h;
    }
    double area(){
        return base * altura;
    }
};

int main() {
    Rectangulo r1(3.0, 2.0);

    cout << "Base: " << r1.base << "\n";
    cout << "Altura: " << r1.altura << "\n";
    cout << "Area: " << r1.area() << "\n";

    return 0;
}