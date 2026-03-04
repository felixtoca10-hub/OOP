import java.util.Scanner;

public class App {

    //Logica del programa
    public static void main(String[] args)  {

        Scanner entrada = new Scanner(System.in);

        Vista vista = new Vista();

        int opcion;

        int n1,n2;
        Numero numero1, numero2, resultado;

        do { 
            vista.mostrarTitulo();
            vista.mostrarMenu();
            try {
                opcion = entrada.nextInt();

                switch (opcion) {
                    case 1:
                        vista.escribirNumero();
                        n1 = entrada.nextInt();
                        vista.escribirNumero();
                        n2 = entrada.nextInt();

                        numero1 = new Numero(n1);
                        numero2 = new Numero(n2);

                        Suma suma = new Suma(numero1 , numero2);
                        resultado = suma.sumar();

                        vista.escribirSalidaS(resultado);
                        break;
                    
                    case 2:
                        vista.escribirNumero();
                        n1 = entrada.nextInt();
                        vista.escribirNumero();
                        n2 = entrada.nextInt();

                        numero1 = new Numero(n1);
                        numero2 = new Numero(n2);

                        Resta resta = new Resta(numero1 , numero2);
                        resultado = resta.restar();

                        vista.escribirSalidaR(resultado);
                        break;
                    
                        case 3:
                        vista.escribirNumero();
                        n1 = entrada.nextInt();
                        vista.escribirNumero();
                        n2 = entrada.nextInt();

                        numero1 = new Numero(n1);
                        numero2 = new Numero(n2);

                        Multiplicacion multiplicacion = new Multiplicacion(numero1 , numero2);
                        resultado = multiplicacion.multiplicar();

                        vista.escribirSalidaM(resultado);
                        break;
                    
                        case 4:
                        vista.escribirNumero();
                        n1 = entrada.nextInt();
                        vista.escribirNumero();
                        n2 = entrada.nextInt();

                        numero1 = new Numero(n1);
                        numero2 = new Numero(n2);

                        Division division = new Division(numero1 , numero2);
                        resultado = division.dividir();

                        vista.escribirSalidaD(resultado);
                        break;
                    
                    case 5:
                        vista.salirPrograma();
                        break;
                    
                    default:
                        vista.mostrarDefault();
                        break;
            }
        } catch (java.util.InputMismatchException e) {
            System.err.println("Error: La entrada no es un número entero. Por favor, intente nuevamente.");
            entrada.nextLine();
            opcion = 0;
        }
        } while (opcion != 5);

        entrada.close();
    }
}