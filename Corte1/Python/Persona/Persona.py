class Persona:
    #constructor
    def __init__(self): 
        self.nombre = ""
        self.años = 0
        self.lugar = ""

    #Metodo
    def say_hello(self):
        print(f"Hola, me llamo {self.nombre} , tengo {self.años} año y soy de {self.lugar}.")

def main ():
    #Crea un objeto
    p1 = Persona()

    #Asigna atributos
    p1.nombre = "Felix"
    p1.años = 19
    p1.lugar = "Bogotá"

    #Llama el metodo
    p1.say_hello()

if __name__=="__main__":
    main()