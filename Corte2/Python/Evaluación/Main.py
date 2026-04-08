# REQUERIMIENTO: Punto de entrada único del programa
from GestorCurso import GestorCurso
from Vista import Vista

def main():
    # Instanciación del controlador y la vista
    curso = GestorCurso()
    interfaz = Vista(curso)
    
    # Lanzador del sistema
    interfaz.ejecutar()

if __name__ == "__main__":
    main()