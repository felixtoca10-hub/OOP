package Persistencia;

import Modelo.Medicion;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Encargado técnico de administrar el ciclo de vida del archivo plano CSV.
 */
public class ManejadorCSV {
    private String rutaArchivo; // Guarda el nombre o ruta del archivo en disco

    // Constructor que recibe el nombre del archivo y gatilla la inicialización de cabeceras
    public ManejadorCSV(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        inicializarArchivo();
    }

    /**
     * Verifica si el archivo plano existe. Si no es así, lo crea desde cero
     * e inyecta la primera fila correspondiente a los títulos de las columnas (Cabecera).
     */
    private void inicializarArchivo() {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) { // Condicional de control de existencia física
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                // Escribe los títulos separados por comas según el estándar del proyecto
                pw.println("fecha,hora,idSensor,tipoSensor,valor,unidad,estado");
            } catch (IOException e) {
                // Captura fallos potenciales de permisos de escritura en el sistema operativo
                System.err.println("Error crítico al inicializar el archivo CSV: " + e.getMessage());
            }
        }
    }

    /**
     * Registra una nueva medición al final del archivo de texto plano.
     * Utiliza la palabra clave 'synchronized' para evitar colisiones de hilos (Thread-safe)
     * cuando múltiples clientes envíen mediciones de forma simultánea.
     */
    public synchronized void registrarMedicion(Medicion med, String fecha, String hora) throws IOException {
        // Abre el archivo en modo 'append' (true) para añadir líneas sin borrar lo anterior
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            pw.println(med.toLineaCSV(fecha, hora)); // Escribe la fila estructurada
        }
    }
}