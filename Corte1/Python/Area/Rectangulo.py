class Rectangulo:
    #Contructor
    def __init__(self, b, h):
        self.base = b
        self.altura = h
    def area(self):
        return self.base * self.altura
    
def main():
    r1 = Rectangulo(3.0, 2.0)
    print("Base:", r1.base)
    print("Altura:", r1.altura)
    print("Area:", r1.area())
if __name__=="__main__":
    main()