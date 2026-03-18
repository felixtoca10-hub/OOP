// Archivo: CalculadoraFiscal.java
import java.util.ArrayList;

public class CalculadoraFiscal {
    private final ArrayList<Gasto> misGastos = new ArrayList<>();

    public void agregarGasto(Gasto g) { misGastos.add(g); }
    public void limpiar() { misGastos.clear(); }
    public boolean tieneDatos() { return !misGastos.isEmpty(); }

    public void mostrarReporte() {
        double sumaTotal = 0;
        System.out.println("\n----------------------------------------");
        System.out.println("         DESGLOSE DE CONSUMO           ");
        System.out.println("----------------------------------------");

        for (Gasto g : misGastos) {
            g.imprimirDetalle(); // Polimorfismo
            sumaTotal += g.getMonto();
        }

        double impuesto23 = sumaTotal * 0.23;
        double impuesto30 = sumaTotal * 0.30;

        System.out.println("----------------------------------------");
        System.out.printf("GASTO TOTAL CONSUMIDO:    $%,.2f%n", sumaTotal);
        System.out.printf("\u001B[32mESTIMADO FAIRTAX (23%%):   $%,.2f%n\u001B[0m", impuesto23);
        System.out.printf("\u001B[33mTASA CRÍTICA (30%%):       $%,.2f%n\u001B[0m", impuesto30);
        System.out.println("----------------------------------------");
        System.out.println("*Nota: El 23% es sobre el total; el 30% es sobre el neto.");
    }
}