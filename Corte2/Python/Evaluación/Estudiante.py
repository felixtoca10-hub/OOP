class Estudiante:
    # REQUERIMIENTO 25: Atributos privados (Encapsulamiento)
    # REQUERIMIENTO 27: Sobrecarga de Constructores
    # En Python se usa un constructor con valores por defecto para simular sobrecarga
    def __init__(self, c="", n="", e=0):
        self.__codigo = c
        self.__nombre = n
        self.__edad = e

    # Getters para acceder a los datos privados
    def get_codigo(self):
        return self.__codigo

    def get_nombre(self):
        return self.__nombre