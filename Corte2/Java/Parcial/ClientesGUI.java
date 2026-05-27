// Archivo: ClientesGUI.java
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;

/**
 * REQUISITO 1: Interfaz gráfica de usuario basada en Java Swing.
 */
public class ClientesGUI extends JFrame {
    // Componentes visuales de la ventana
    private JTextField txtAutor;
    private JTextArea txtDescripcion;
    private JComboBox<String> cmbTipoReporte;
    private JTextField txtCampoExtra;
    private JLabel lblCampoExtra;
    private JButton btnEnviar;
    private JTextArea txtConsolaPolimorfismo; // Para evidenciar el polimorfismo localmente

    /**
     * Constructor que configura las propiedades fundamentales de la ventana Swing.
     */
    public ClientesGUI() {
        setTitle("Sistema de Reportes - Cliente Oficial");
        setSize(520, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        initComponents();            // Construye los paneles y elementos
    }

    /**
     * Inicialización y distribución estética de los componentes visuales.
     */
    private void initComponents() {
        // Panel del Formulario (Cuadrícula de 5 filas y 2 columnas con márgenes)
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panelFormulario.add(new JLabel("Autor del Reporte:"));
        txtAutor = new JTextField();
        panelFormulario.add(txtAutor);

        panelFormulario.add(new JLabel("Tipo de Incidencia:"));
        String[] opciones = {"Técnico", "General"};
        cmbTipoReporte = new JComboBox<>(opciones);
        panelFormulario.add(cmbTipoReporte);

        lblCampoExtra = new JLabel("Área Técnica:");
        txtCampoExtra = new JTextField();
        panelFormulario.add(lblCampoExtra);
        panelFormulario.add(txtCampoExtra);

        panelFormulario.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true); // Hace que el texto salte de línea automáticamente
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        panelFormulario.add(scrollDesc);

        btnEnviar = new JButton("Enviar Reporte por Socket");
        panelFormulario.add(new JLabel("")); // Celda vacía para cuadrar la distribución del botón
        panelFormulario.add(btnEnviar);

        // Panel de Consola: Diseñado específicamente para demostrar el Polimorfismo al profesor
        JPanel panelConsola = new JPanel(new BorderLayout());
        panelConsola.setBorder(BorderFactory.createTitledBorder("Consola de Polimorfismo (Demostración local)"));
        txtConsolaPolimorfismo = new JTextArea(6, 40);
        txtConsolaPolimorfismo.setEditable(false);
        txtConsolaPolimorfismo.setBackground(Color.BLACK);
        txtConsolaPolimorfismo.setForeground(Color.GREEN); // Estilo terminal clásica
        panelConsola.add(new JScrollPane(txtConsolaPolimorfismo), BorderLayout.CENTER);

        // Ubicar paneles en el contenedor principal usando BorderLayout
        setLayout(new BorderLayout());
        add(panelFormulario, BorderLayout.NORTH);
        add(panelConsola, BorderLayout.CENTER);

        // Listener dinámico: cambia la etiqueta de texto según el tipo seleccionado en el JComboBox
        cmbTipoReporte.addActionListener(e -> {
            if (cmbTipoReporte.getSelectedIndex() == 0) {
                lblCampoExtra.setText("Área Técnica:");
            } else {
                lblCampoExtra.setText("Categoría:");
            }
        });

        // Configurar la acción del botón de envío
        btnEnviar.addActionListener(e -> procesarYEnviarReporte());
    }

    /**
     * Orquestador que valida los datos, ejecuta el polimorfismo y lanza el hilo de red.
     */
    private void procesarYEnviarReporte() {
        String autor = txtAutor.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String tipo = (String) cmbTipoReporte.getSelectedItem();
        String extra = txtCampoExtra.getText().trim();

        // Validación básica de campos obligatorios
        if (autor.isEmpty() || descripcion.isEmpty() || extra.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son requeridos.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String fechaHoy = LocalDate.now().toString();

        // === REQUISITO 4: USO CLARO DE POLIMORFISMO ===
        // Declaramos una variable de referencia de la clase BASE (Reporte)
        Reporte miReporte;

        // Instanciamos dinámicamente un objeto hijo u otro basándonos en la selección del usuario
        if (tipo.equals("Técnico")) {
            miReporte = new ReporteTecnico(autor, descripcion, fechaHoy, extra);
        } else {
            miReporte = new ReporteGeneral(autor, descripcion, fechaHoy, extra);
        }

        // Aquí ocurre el Polimorfismo: Java sabe en tiempo de ejecución a cuál de los métodos
        // 'formatear()' llamar basándose en el objeto real de la variable.
        txtConsolaPolimorfismo.append("[POLIMORFISMO] Método ejecutado: \n" + miReporte.formatear() + "\n\n");

        // Bloquear el botón temporalmente para evitar clics dobles rápidos
        btnEnviar.setEnabled(false);
        btnEnviar.setText("Conectando con Servidor...");

        // === REQUISITO 6: USO DE UN HILO PARA NO CONGELAR LA INTERFAZ ===
        new Thread(() -> {
            try {
                // REQUISITO 7: Conexión mediante Sockets a localhost en el puerto asignado
                Socket socket = new Socket("localhost", 12345);
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Enviar la información de forma estructurada línea por línea hacia el servidor
                salida.println(tipo);
                salida.println(autor);
                salida.println(descripcion);
                salida.println(extra);

                // Quedarse esperando la respuesta del servidor
                String respuestaServidor = entrada.readLine();
                
                // Cerrar recursos de red
                socket.close();

                // Actualizar componentes gráficos de forma segura usando invokeLater
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, respuestaServidor, "Respuesta del Servidor", JOptionPane.INFORMATION_MESSAGE);
                    limpiarFormulario();
                });

            } catch (Exception ex) {
                // Capturar errores si el servidor está apagado o fuera de línea
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Error: No se pudo conectar con el servidor.\n¿Verificaste que esté encendido?", "Fallo de Socket", JOptionPane.ERROR_MESSAGE);
                });
            } finally {
                // Al terminar todo el proceso, rehabilitamos el botón en la GUI
                SwingUtilities.invokeLater(() -> {
                    btnEnviar.setEnabled(true);
                    btnEnviar.setText("Enviar Reporte por Socket");
                });
            }
        }).start(); // Inicia la ejecución del hilo de red de forma asíncrona
    }

    private void limpiarFormulario() {
        txtAutor.setText("");
        txtDescripcion.setText("");
        txtCampoExtra.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClientesGUI().setVisible(true);
        });
    }
}