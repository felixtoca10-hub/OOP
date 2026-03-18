// Archivo: Gasto.java
public abstract class Gasto {
    private final String categoria;
    private double monto;

    public Gasto(String categoria, double monto) {
        this.categoria = categoria;
        setMonto(monto);
    }

    public String getCategoria() { return categoria; }
    public double getMonto() { return monto; }

    public final void setMonto(double monto) {
        this.monto = (monto < 0) ? 0 : monto;
    }

    public abstract void imprimirDetalle();

    public double calcularImpuesto23() { return this.monto * 0.23; }
    public double calcularImpuesto30() { return this.monto * 0.30; }
}