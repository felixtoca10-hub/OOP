class Calculadora:
    # Método que procesa un arreglo de notas para devolver el promedio
    def obtener_promedio(self, notas, cantidad):
        suma = 0.0
        # REQUERIMIENTO: Uso de ciclos para recorrer estructuras
        for i in range(cantidad):
            suma += notas[i]
        
        return (suma / cantidad) if cantidad > 0 else 0.0