from abc import ABC, abstractmethod

class Figura(ABC):
    def __init__(self, nombre):
        # En Python, el atributo es accesible por las clases hijas [cite: 57, 62]
        self.nombre = nombre
    
    @abstractmethod
    def calcular_area(self):
        """Método abstracto: cada figura hija debe implementar su propia fórmula [cite: 46, 59]"""
        pass

    def mostrar_info(self):
        # Método común para todas las figuras [cite: 60]
        print(f"Figura: {self.nombre} | Area: {self.calcular_area()}")