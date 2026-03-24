// REQUERIMIENTO: Punto de entrada único del programa
#include "Vista.cpp"

int main() {
    // Instanciación del controlador y la vista
    GestorCurso curso;
    Vista interfaz(curso);
    
    // Lanzador del sistema
    interfaz.ejecutar(); 
    
    return 0;
}