package Modelo; // Define el paquete al que pertenece la clase

import java.util.Locale; // Importa la configuración regional para asegurar el uso del punto decimal (.)

/**
 * Clase que representa una lectura o medición individual de un sensor.
 * Modela la estructura de datos que se transmite por red y se almacena en disco.
 */
public class Medicion {
    // Atributos privados para garantizar el Encapsulamiento
    private String idSensor;    // Identificador único del sensor (ej: "S01")
    private String tipoSensor;  // Tipo de variable (TEMPERATURA, VOLTAJE, DISTANCIA)
    private double valor;       // Valor numérico de la medición
    private String unidad;      // Unidad de medida (C, V, m)
    private String estado;      // Estado evaluado (NORMAL, ALERTA, BATERIA_BAJA, etc.)

    // Constructor completo para inicializar todos los atributos del objeto
    public Medicion(String idSensor, String tipoSensor, double valor, String unidad, String estado) {
        this.idSensor = idSensor;
        this.tipoSensor = tipoSensor;
        this.valor = valor;
        this.unidad = unidad;
        this.estado = estado;
    }

    // Métodos Getter y Setter (Encapsulamiento) para acceso controlado a los atributos
    public String getIdSensor() { return idSensor; }
    public String getTipoSensor() { return tipoSensor; }
    public double getValor() { return valor; }
    public String getUnidad() { return unidad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    /**
     * Formatea el valor numérico a dos esquemas decimales fijos usando el punto (.) como separador.
     * Evita errores de lectura si el sistema operativo está en español (que usa la coma).
     */
    public String valorComoTexto() {
        return String.format(Locale.US, "%.2f", valor);
    }

    /**
     * Serializa el objeto en una cadena de texto plana para ser enviada a través de Sockets.
     * Formato: ID;TIPO;VALOR;UNIDAD
     */
    public String toMensajeSocket() {
        return idSensor + ";" + tipoSensor + ";" + valorComoTexto() + ";" + unidad;
    }

    /**
     * Convierte la medición en una línea estructurada apta para registros de archivos CSV.
     * Formato: fecha,hora,idSensor,tipoSensor,valor,unidad,estado
     */
    public String toLineaCSV(String fecha, String hora) {
        return fecha + "," + hora + "," + idSensor + "," + tipoSensor + "," + valorComoTexto() + "," + unidad + "," + estado;
    }
}