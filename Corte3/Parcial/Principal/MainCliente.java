package Principal;

import Cliente.VentanaCliente;
import javax.swing.SwingUtilities;

/**
 * Lanzador ejecutable exclusivo para instanciar las estaciones clientes simuladas.
 */
public class MainCliente {
    public static void main(String[] args) {
        // Despacha la inicialización gráfica del nodo cliente
        SwingUtilities.invokeLater(() -> {
            new VentanaCliente().setVisible(true); // Hace visible la pantalla de simulación cliente
        });
    }
}