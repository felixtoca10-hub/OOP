// Archivo: Main.java
import javax.swing.SwingUtilities;

/**
 * Clase principal encargada de automatizar el encendido de todo el sistema.
 * Como no usamos carpetas, levanta el servidor local en un hilo y abre el cliente inmediatamente.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA INTEGRADO (MODO PLANO) ===");

        // 1. Lanzamos el servidor en un hilo de fondo (Background Thread).
        // Así evitamos que su bucle de escucha bloquee la ejecución de la GUI.
        Thread hiloServidor = new Thread(() -> {
            try {
                // Arrancamos el método main del Servidor sin salirnos de esta clase
                ServidorReportes.main(new String[0]);
            } catch (Exception e) {
                System.err.println("Error al levantar el servidor automatizado: " + e.getMessage());
            }
        });
        
        hiloServidor.start();

        // 2. Pausa de sincronización (2 segundos).
        // Le damos un momento al hilo del servidor para que reserve el puerto 12345 con éxito
        try {
            System.out.println("Sincronizando hilos... Esperando a que el servidor abra el puerto de escucha...");
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 3. Lanzamos la interfaz gráfica de Swing usando la clase ClientesGUI corregida
        System.out.println("Abriendo la interfaz gráfica del Cliente...");
        SwingUtilities.invokeLater(() -> {
            new ClientesGUI().setVisible(true);
        });
    }
}