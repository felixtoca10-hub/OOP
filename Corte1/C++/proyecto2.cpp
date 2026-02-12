#include <iostream>
#include <string>
using namespace std;

//Primera clase: entender "clase" vs "objeto"
class Persona {
public:
	string nombre; //Atributo
	int edad; //Atributo

	//Método (función dentro de una clase)
	void saludar(){
		cout << "Hola, soy " << nombre << " y tengo " << edad << " años.\n";
	}
};
int main() {
	//Crear un objeto (una " Persona" real)
	Persona p1;
	//Asignar valores a los atributos
	p1.nombre = "Felix";
	p1.edad = 19;
	//Llamar un metodo del objeto
	p1.saludar();

	Persona p2;
	p2.nombre = "Capi";
	p2.edad = 18;
	p2.saludar();
	return 0;
}
