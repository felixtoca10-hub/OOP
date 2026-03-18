// Archivo: Main.java
public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        CalculadoraFiscal calc = new CalculadoraFiscal();
        
        String[] categorias = {
            "Alojamiento", "Comida", "Ropa", "Transporte", 
            "Educación", "Servicios Médicos", "Vacaciones"
        };
        
        int opcion = 0;

        do {
            vista.mostrarTitulo();
            opcion = vista.mostrarMenuYLeerOpcion();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- INGRESO DE GASTOS ---");
                    for (String cat : categorias) {
                        double monto = vista.leerGasto(cat);
                        calc.agregarGasto(new GastoConsumo(cat, monto));
                    }
                    vista.imprimirExito("Gastos registrados.");
                    break;
                case 2:
                    if (calc.tieneDatos()) {
                        calc.mostrarReporte();
                    } else {
                        vista.imprimirError("No hay datos. Use la opción 1.");
                    }
                    break;
                case 3:
                    calc.limpiar();
                    vista.imprimirExito("Datos eliminados correctamente.");
                    break;
                case 4:
                    vista.imprimirExito("Saliendo del sistema...");
                    break;
                default:
                    vista.imprimirError("Opción no válida.");
                    break;
            }
        } while (opcion != 4);
    }
}