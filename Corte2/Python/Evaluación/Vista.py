from Estudiante import Estudiante
from Configuracion import MAX_NOTAS

class Vista:
    def __init__(self, g):
        self.__gestor = g

    # VALIDACIÓN DE ROBUSTEZ: Evita que el programa se rompa si ingresan letras
    def __leer_entero(self, mensaje):
        while True:
            try:
                valor = int(input(mensaje))
                return valor
            except ValueError:
                print(">> ERROR: Debe ingresar solo numeros: ", end="")

    # REQUERIMIENTO 23 y 45: Menú Principal
    def ejecutar(self):
        op = 0
        print("=== SISTEMA DE GESTION ACADEMICA ===")
        while op != 4:
            print("\n1. Registrar Alumno\n2. Calificar Alumno\n3. Ver Reporte\n4. Salir")
            op = self.__leer_entero("\nOpcion: ")

            if op == 1: self.__capturar()
            elif op == 2: self.__calificar()
            elif op == 3: self.__reporte()

    def __capturar(self):
        c = input("Codigo: ")
        n = input("Nombre: ")
        e = self.__leer_entero("Edad: ")
        if self.__gestor.registrar(Estudiante(c, n, e)):
            print(">> Alumno guardado.")
        else:
            print(">> Memoria llena.")

    def __calificar(self):
        c = input("Ingrese el codigo del estudiante: ")
        idx = self.__gestor.buscar(c)

        # MENSAJE DE ERROR: Estudiante no encontrado
        if idx == -1:
            print(f">> ERROR: El estudiante con codigo ({c}) no esta registrado.")
        else:
            print(f"Calificando a: {self.__gestor.get_est(idx).get_nombre()}")
            for j in range(MAX_NOTAS):
                while True:
                    try:
                        v = float(input(f"Nota {j+1} (0-5): "))
                        if 0 <= v <= 5:
                            self.__gestor.set_nota(idx, j, v)
                            break
                        else:
                            print("Error. Use numeros entre 0 y 5: ", end="")
                    except ValueError:
                        print("Error. Ingrese un valor numerico: ", end="")

    def __reporte(self):
        if self.__gestor.get_cont() == 0:
            print(">> No hay datos registrados.")
            return
        
        print(f"\n{'ESTUDIANTE':<15} | {'PROMEDIO'}")
        print("-" * 35)
        for i in range(self.__gestor.get_cont()):
            nombre = self.__gestor.get_est(i).get_nombre()
            promedio = self.__gestor.get_promedio(i)
            print(f"{nombre:<15} | {promedio:.2f}")