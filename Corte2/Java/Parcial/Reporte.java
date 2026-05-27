// Archivo: Reporte.java
import java.io.Serializable;

/**
 * Clase base que representa un reporte genérico.
 * Implementa Serializable para permitir su manejo estructurado.
 */
public class Reporte implements Serializable {
    // Atributo de control de versión para la serialización
    private static final long serialVersionUID = 1L;
    
    // REQUISITO 3: Atributos privados (Encapsulamiento)
    private String autor;
    private String descripcion;
    private String fecha;

    /**
     * Constructor de la clase base Reporte.
     */
    public Reporte(String autor, String descripcion, String fecha) {
        this.autor = autor;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // --- REQUISITO 3: Métodos Getters y Setters ---
    
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Método polimórfico básico. Será sobrescrito por las clases derivadas
     * para demostrar el comportamiento dinámico en tiempo de ejecución.
     */
    public String formatear() {
        return "Fecha: " + fecha + " | Autor: " + autor + " | Descripción: " + descripcion;
    }
}