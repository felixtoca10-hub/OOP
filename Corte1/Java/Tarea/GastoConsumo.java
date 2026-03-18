public class GastoConsumo extends Gasto {
    
    // Herencia
    public GastoConsumo(String categoria, double monto) {
        super(categoria, monto); // Llama al constructor de la superclase
    }

    @Override
    public void imprimirRecibo() {
        System.out.printf("Item: %-18s | Subtotal: $%,.2f%n", getCategoria(), getMonto());
    }
}
