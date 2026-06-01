package Principal;

import Servidor.VentanaServidor;
import javax.swing.SwingUtilities;

/**
 * Lanzador ejecutable exclusivo para arrancar el Servidor Centralizado de Monitoreo.
 */
public class MainServidor {
    public static void main(String[] args) {
        // Ejecuta el arranque de la ventana de forma segura dentro del hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            new VentanaServidor().setVisible(true); // Hace visible la pantalla del servidor
        });
    }
}