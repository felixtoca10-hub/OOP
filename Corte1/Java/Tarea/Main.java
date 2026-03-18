// Archivo: Main.java
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CalculadoraFiscal calc = new CalculadoraFiscal();
        int opcion = 0;

        do {
            try {
                System.out.println("\u001B[34m ==================== \u001B[0m");
                System.out.println("\u001B[34m SISTEMA FAIRTAX 5.32 \u001B[0m");
                System.out.println("\u001B[34m ==================== \u001B[0m\n");
                System.out.println("1. Registrar Gastos Mensuales");
                System.out.println("2. Ver Reporte Fiscal");
                System.out.println("3. Reiniciar Datos");
                System.out.println("4. Salir");
                System.out.print("Selección: ");
                
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        ingresarGastosSeguros(sc, calc);
                        break;
                    case 2:
                        calc.generarReporte();
                        break;
                    case 3:
                        calc.limpiarDatos();
                        System.out.println("\u001B[32mDatos borrados.\u001B[0m");
                        break;
                }
            } catch (InputMismatchException e) {
                // Si el usuario digita una letra, entra aquí
                System.out.println("\u001B[31m[Error]: Debe ingresar un número, no letras.\u001B[0m");
                sc.nextLine(); // Limpia el error del teclado para que no se cicle
                opcion = 0;    // Reinicia la opción para que el menú vuelva a aparecer
            }
        } while (opcion != 4);
        
        System.out.println("Programa finalizado.");
    }

    // Método auxiliar para pedir los montos sin que falle
    private static void ingresarGastosSeguros(Scanner sc, CalculadoraFiscal calc) {
        String[] categoriasReq = {"Alojamiento", "Comida", "Ropa", "Transporte", "Educación", "Servicios Médicos", "Vacaciones"};

        for (String cat : categoriasReq) {
            boolean datoValido = false;
            while (!datoValido) {
                try {
                    System.out.print("Ingrese monto para " + cat + ": ");
                    double m = sc.nextDouble();
                    calc.agregarGasto(new GastoConsumo(cat, m));
                    datoValido = true; // Si llega aquí, el dato fue un número
                } catch (InputMismatchException e) {
                    System.out.println("\u001B[33m(!) Valor inválido. Por favor use números (ej: 1500.50).\u001B[0m");
                    sc.nextLine(); // Limpia el buffer del teclado
                }
            }
        }
    }
}