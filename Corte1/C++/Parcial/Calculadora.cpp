#ifndef CALCULADORA_CPP
#define CALCULADORA_CPP

class Calculadora {
public:
    // Método que procesa un arreglo de notas para devolver el promedio
    float obtenerPromedio(float notas[], int cantidad) {
        float suma = 0;
        // REQUERIMIENTO: Uso de ciclos para recorrer estructuras
        for (int i = 0; i < cantidad; i++) {
            suma += notas[i];
        }
        return (cantidad > 0) ? (suma / cantidad) : 0.0f;
    }
};
#endif