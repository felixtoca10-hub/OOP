// Archivo: GastoConsumo.java
public class GastoConsumo extends Gasto {
    public GastoConsumo(String categoria, double monto) {
        super(categoria, monto);
    }

    @Override
    public void imprimirDetalle() {
        System.out.printf(" >> %-18s | Monto: $%,.2f%n", getCategoria(), getMonto());
    }
}