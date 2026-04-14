from Circulo import Circulo
from Rectangulo import Rectangulo

def main():
    # El polimorfismo permite almacenar objetos distintos en una misma colección [cite: 159, 164]
    figuras = [
        Circulo(5.0),
        Rectangulo(10.0, 4.0)
    ]

    # Al recorrer la lista, cada objeto ejecuta su propia versión de calcularArea() [cite: 26, 165]
    for f in figuras:
        f.mostrar_info()

if __name__ == "__main__":
    main()