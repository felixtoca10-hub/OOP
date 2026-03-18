// Archivo: VistaFairTax.java
import java.util.InputMismatchException;
import java.util.Scanner;

public class Vista {
    private final Scanner sc = new Scanner(System.in);

    // Colores ANSI
    private final String RESET = "\u001B[0m";
    private final String AZUL = "\u001B[34m";
    private final String VERDE = "\u001B[32m";
    private final String ROJO = "\u001B[31m";
    private final String AMARILLO = "\u001B[33m";

    public void mostrarTitulo() {
        System.out.println(AZUL + "========================================");
        System.out.println("   MENÚ DE ESTIMACIÓN FAIRTAX 5.32   ");
        System.out.println("========================================" + RESET);
    }

    public int mostrarMenuYLeerOpcion() {
        System.out.println("1. Ingresar gastos por categorías");
        System.out.println("2. Ver reporte detallado y FairTax");
        System.out.println("3. Limpiar todos los datos");
        System.out.println(ROJO + "4. Salir" + RESET);
        System.out.print("Seleccione una opción: ");
        
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine(); // Limpiar el buffer si el usuario pone letras
            return -1; // Retorna error para el switch
        }
    }

    public double leerGasto(String cat) {
        while (true) {
            try {
                System.out.print("Gasto estimado para " + AMARILLO + cat + RESET + ": ");
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(ROJO + "✘ Error: Ingrese un número válido." + RESET);
                sc.nextLine();
            }
        }
    }

    public void imprimirExito(String msj) { System.out.println(VERDE + "✔ " + msj + RESET); }
    public void imprimirError(String msj) { System.out.println(ROJO + "✘ " + msj + RESET); }
}