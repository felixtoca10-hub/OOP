#ifndef GESTORCURSO_CPP
#define GESTORCURSO_CPP
#include "Configuracion.cpp"
#include "Estudiante.cpp"
#include "Calculadora.cpp"

class GestorCurso {
private:
    // REQUERIMIENTO 31: Arreglo de Objetos
    Estudiante lista[MAX_ESTUDIANTES];
    
    // REQUERIMIENTO 32: Matriz de datos (float)
    float matriz[MAX_ESTUDIANTES][MAX_NOTAS];
    
    int contador;
    Calculadora calc;

public:
    GestorCurso() : contador(0) {
        // Inicialización de matriz con ceros
        for(int i = 0; i < MAX_ESTUDIANTES; i++)
            for(int j = 0; j < MAX_NOTAS; j++) matriz[i][j] = 0.0f;
    }

    bool registrar(Estudiante e) {
        if (contador < MAX_ESTUDIANTES) {
            lista[contador] = e;
            contador++;
            return true;
        }
        return false;
    }

    // REQUERIMIENTO: Método de búsqueda en el arreglo
    int buscar(std::string cod) {
        for (int i = 0; i < contador; i++) {
            if (lista[i].getCodigo() == cod) return i;
        }
        return -1; // Retorna -1 si el estudiante no existe
    }

    void setNota(int f, int c, float v) { matriz[f][c] = v; }
    int getCont() { return contador; }
    Estudiante getEst(int i) { return lista[i]; }
    float getPromedio(int i) { return calc.obtenerPromedio(matriz[i], MAX_NOTAS); }
};
#endif