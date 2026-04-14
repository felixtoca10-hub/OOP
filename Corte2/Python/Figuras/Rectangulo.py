from Figura import Figura

class Rectangulo(Figura):
    def __init__(self, base, altura):
        super().__init__("Rectangulo")
        self.base = base
        self.altura = altura

    def calcular_area(self):
        # Implementa la fórmula específica del rectángulo [cite: 30, 136]
        return self.base * self.altura