import java.util.Scanner;

public class App {

    //Logica del programa
    public static void main(String[] args)  {
        Numero numero1 = new Numero();
        Numero numero2 = new Numero();
        Numero multiplicacion = new Numero();
        Scanner teclado = new Scanner(System.in);
        Vista vista = new Vista();
        vista.mostrarTitulo();
        vista.mostrarNumero();
        int n1 = teclado .nextInt();
        numero1.setNumero(n1);
        vista.mostrarNumero();
        int n2 = teclado .nextInt();
        numero2.setNumero(n2);
        int m = numero1.getNumero() *numero2.getNumero();
        multiplicacion.setNumero(m);
        vista.mostrarResultado();
        vista.mostrarSalida(multiplicacion);
        teclado.close();
    }
}
