import java.util.ArrayList;

public class CalculadoraFiscal {
    private ArrayList<Gasto> listaGastos = new ArrayList<>();

    public void agregarGasto(Gasto g) {
        listaGastos.add(g);
    }

    public void limpiarDatos() {
        listaGastos.clear();
    }

    public void generarReporte() {
        if (listaGastos.isEmpty()) {
            System.out.println("No hay datos para procesar.");
            return;
        }

        double totalConsumo = 0;
        double totalTax23 = 0;

        System.out.println("\n--- DESGLOSE FAIRTAX ---");
        
        // Polimorfismo
        // Tratamos a todos como 'Gasto', pero actúan como 'GastoConsumo'
        for (Gasto g : listaGastos) {
            g.imprimirRecibo(); 
            totalConsumo += g.getMonto();
            totalTax23 += g.calcularImpuesto23();
        }

        System.out.println("-------------------------");
        System.out.printf("TOTAL CONSUMO: $%,.2f%n", totalConsumo);
        System.out.printf("FAIRTAX (23%%): $%,.2f%n", totalTax23);
        System.out.printf("EFECTIVO (30%%): $%,.2f%n", (totalConsumo * 0.30));
        System.out.println("-------------------------");
    }
}