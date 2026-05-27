// Archivo: ReporteGeneral.java

/**
 * Clase derivada que hereda de Reporte (Especialización general).
 */
public class ReporteGeneral extends Reporte {
    // Atributo privado específico para reportes generales
    private String categoria;

    /**
     * Constructor de ReporteGeneral.
     */
    public ReporteGeneral(String autor, String descripcion, String fecha, String categoria) {
        super(autor, descripcion, fecha); // Llama al constructor de Reporte
        this.categoria = categoria;
    }

    // Getter y Setter para el atributo específico
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * REQUISITO 4 (Polimorfismo): Sobrescribe el método formatear() y cambia su comportamiento.
     */
    @Override
    public String formatear() {
        return super.formatear() + " | [GENERAL] Categoría: " + categoria;
    }
}