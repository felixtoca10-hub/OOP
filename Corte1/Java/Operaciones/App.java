import java.util.Scanner;

public class App {

    //Logica del programa
    public static void main(String[] args)  {

        Scanner entrada = new Scanner(System.in);

        Vista vista = new Vista();

        int opcion;

        do { 
            vista.mostrarMenu();
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    vista.escribirNumero();
                    int n1 = entrada.nextInt();
                    vista.escribirNumero();
                    int n2 = entrada.nextInt();

                    Numero numero1 = new Numero(n1);
                    Numero numero2 = new Numero(n2);

                    Suma suma = new Suma(numero1 , numero2);
                    Numero resultado = suma.sumar();

                    vista.escribirSalida(resultado);
                    break;
                case 2:
                    vista.salirPrograma();
                    break;
                default:
                    vista.mostrarDefault();
                    break;
            }
        } while (opcion != 2);

        entrada.close();
    }
}
