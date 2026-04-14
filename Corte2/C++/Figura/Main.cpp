public class Main {
    public static void main ( String [] args ) {
        Figura [] figuras = {
            new Circulo (5.0) ,
            new Rectangulo (10.0 , 4.0)
        };

        for ( Figura f : figuras ) {
            f . mostrarInfo () ;
        }
    }
}