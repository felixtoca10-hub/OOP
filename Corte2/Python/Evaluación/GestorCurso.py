from Configuracion import MAX_ESTUDIANTES, MAX_NOTAS
from Estudiante import Estudiante
from Calculadora import Calculadora

class GestorCurso:
    def __init__(self):
        # REQUERIMIENTO 31: Arreglo de Objetos (Inicializado con None)
        self.__lista = [None] * MAX_ESTUDIANTES
        
        # REQUERIMIENTO 32: Matriz de datos (float)
        # Inicialización de matriz con ceros
        self.__matriz = [[0.0 for _ in range(MAX_NOTAS)] for _ in range(MAX_ESTUDIANTES)]
        
        self.__contador = 0
        self.__calc = Calculadora()

    def registrar(self, e):
        if self.__contador < MAX_ESTUDIANTES:
            self.__lista[self.__contador] = e
            self.__contador += 1
            return True
        return False

    # REQUERIMIENTO: Método de búsqueda en el arreglo
    def buscar(self, cod):
        for i in range(self.__contador):
            if self.__lista[i].get_codigo() == cod:
                return i
        return -1  # Retorna -1 si el estudiante no existe

    def set_nota(self, f, c, v):
        self.__matriz[f][c] = v

    def get_cont(self):
        return self.__contador

    def get_est(self, i):
        return self.__lista[i]

    def get_promedio(self, i):
        return self.__calc.obtener_promedio(self.__matriz[i], MAX_NOTAS)