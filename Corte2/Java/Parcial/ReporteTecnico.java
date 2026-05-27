// Archivo: ReporteTecnico.java

/**
 * Clase derivada que hereda de Reporte (Especialización técnica).
 */
public class ReporteTecnico extends Reporte {
    // Atributo privado específico para reportes técnicos
    private String area;

    /**
     * Constructor de ReporteTecnico. Usa 'super' para invocar al constructor padre.
     */
    public ReporteTecnico(String autor, String descripcion, String fecha, String area) {
        super(autor, descripcion, fecha); // Llama al constructor de Reporte
        this.area = area;
    }

    // Getter y Setter para el atributo específico
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    /**
     * REQUISITO 4 (Polimorfismo): Sobrescribe el método formatear() agregando el campo 'area'.
     */
    @Override
    public String formatear() {
        // Usa super.formatear() para reutilizar la lógica de la clase padre
        return super.formatear() + " | [TÉCNICO] Área afectada: " + area;
    }
}