#ifndef VISTA_CPP
#define VISTA_CPP
#include <iostream>
#include <iomanip>
#include <string>
#include "GestorCurso.cpp"

using namespace std;

class Vista {
private:
    GestorCurso& gestor;

    // VALIDACIÓN DE ROBUSTEZ: Evita que el programa se rompa si ingresan letras
    int leerEntero(string mensaje) {
        int valor;
        cout << mensaje;
        while (!(cin >> valor)) {
            cout << ">> [!] ERROR: Debe ingresar solo numeros: ";
            cin.clear();
            cin.ignore(1000, '\n');
        }
        return valor;
    }

public:
    Vista(GestorCurso& g) : gestor(g) {}

    // REQUERIMIENTO 23 y 45: Menú Principal
    void ejecutar() {
        int op = 0;
        cout << "=== SISTEMA DE GESTION ACADEMICA ===" << endl;
        while (op != 4) {
            cout << "\n1. Registrar Alumno\n2. Calificar Alumno\n3. Ver Reporte\n4. Salir\n\nOpcion: ";
            op = leerEntero("");

            if (op == 1) capturar();
            else if (op == 2) calificar();
            else if (op == 3) reporte();
        }
    }

private:
    void capturar() {
        string c, n;
        cout << "Codigo: "; cin >> c;
        cout << "Nombre: "; cin.ignore(); getline(cin, n);
        int e = leerEntero("Edad: ");
        if(gestor.registrar(Estudiante(c, n, e))) cout << ">> Alumno guardado.\n";
        else cout << ">> [!] Memoria llena.\n";
    }

    void calificar() {
        string c;
        cout << "Ingrese el codigo del estudiante: "; cin >> c;
        int idx = gestor.buscar(c);

        // MENSAJE DE ERROR: Estudiante no encontrado
        if(idx == -1) {
            cout << ">> [!] ERROR: El estudiante con codigo (" << c << ") no esta registrado." << endl;
        } else {
            cout << "Calificando a: " << gestor.getEst(idx).getNombre() << endl;
            for(int j=0; j < MAX_NOTAS; j++) {
                float v;
                cout << "Nota " << j+1 << " (0-5): ";
                while(!(cin >> v) || v < 0 || v > 5) {
                    cout << "Error. Use numeros entre 0 y 5: ";
                    cin.clear(); cin.ignore(1000, '\n');
                }
                gestor.setNota(idx, j, v);
            }
        }
    }

    void reporte() {
        if(gestor.getCont() == 0) {
            cout << ">> No hay datos registrados." << endl;
            return;
        }
        cout << "\n" << left << setw(15) << "ESTUDIANTE" << " | " << "PROMEDIO" << endl;
        cout << "-----------------------------------" << endl;
        for(int i=0; i < gestor.getCont(); i++) {
            cout << left << setw(15) << gestor.getEst(i).getNombre() 
                 << " | " << fixed << setprecision(2) << gestor.getPromedio(i) << endl;
        }
    }
};
#endif