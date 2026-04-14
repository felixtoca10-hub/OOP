import math
from Figura import Figura

class Circulo(Figura):
    def __init__(self, radio):
        # Llama al constructor de la clase padre (Figura) [cite: 77, 128]
        super().__init__("Circulo")
        self.radio = radio

    def calcular_area(self):
        # Implementa la fórmula específica del círculo [cite: 29, 135]
        return math.pi * math.pow(self.radio, 2)