package Cliente;

import Modelo.*; // Importa todas las clases del modelo para usarlas de forma orientada a objetos

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Interfaz gráfica del Cliente de Simulación. Construye objetos dinámicamente y los transmite por Sockets.
 */
public class VentanaCliente extends JFrame {
    // Definición de componentes visuales de interfaz de usuario
    private JTextField txtIp;
    private JTextField txtPuerto;
    private JTextField txtIdSensor;
    private JComboBox<String> cbTipoSensor;
    private JTextField txtValorSimulado;
    private JButton btnConectar;
    private JButton btnGenerar;
    private JButton btnEnviar;
    private JTextArea txtConsola;

    // Componentes lógicos de conectividad de red y encapsulamiento de datos
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    private Medicion medicionActual; // Almacena el objeto Medicion generado en memoria (POO)

    // Constructor: Diseña los paneles gráficos de control y simulación
    public VentanaCliente() {
        setTitle("Estación de Medición - Nodo Cliente");
        setSize(460, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana sin apagar el servidor si se ejecutan juntos
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- CONSTRUCCIÓN PANEL CONFIGURACIÓN DE RED ---
        JPanel panelRed = new JPanel(new GridLayout(2, 3, 5, 5));
        panelRed.setBorder(BorderFactory.createTitledBorder("Parámetros de Enlace de Red"));
        panelRed.add(new JLabel("IP Servidor:"));
        txtIp = new JTextField("localhost"); // Localhost por defecto para pruebas en la misma máquina
        panelRed.add(txtIp);
        btnConectar = new JButton("Conectar");
        panelRed.add(btnConectar);

        panelRed.add(new JLabel("Puerto Central:"));
        txtPuerto = new JTextField("5000");
        panelRed.add(txtPuerto);
        add(panelRed, BorderLayout.NORTH);

        // --- CONSTRUCCIÓN PANEL CONTROL DEL SENSOR (APLICACIÓN POO) ---
        JPanel panelSensor = new JPanel(new GridLayout(4, 2, 8, 8));
        panelSensor.setBorder(BorderFactory.createTitledBorder("Módulo de Sensado e Instrumentación (POO)"));
        
        panelSensor.add(new JLabel("ID Identificador:"));
        txtIdSensor = new JTextField("S01");
        panelSensor.add(txtIdSensor);

        panelSensor.add(new JLabel("Tipo Magnitud:"));
        cbTipoSensor = new JComboBox<>(new String[]{"TEMPERATURA", "VOLTAJE", "DISTANCIA"});
        panelSensor.add(cbTipoSensor);

        btnGenerar = new JButton("1. Generar Objeto");
        btnGenerar.setEnabled(false); // Deshabilitado hasta que haya una conexión de red activa
        panelSensor.add(btnGenerar);
        
        txtValorSimulado = new JTextField();
        txtValorSimulado.setEditable(false); // Bloqueado, el valor proviene del cálculo de los objetos del modelo
        panelSensor.add(txtValorSimulado);

        btnEnviar = new JButton("2. Enviar por Socket");
        btnEnviar.setEnabled(false);
        panelSensor.add(btnEnviar);
        panelSensor.add(new JLabel("<- Transmitir Trama", JLabel.CENTER));

        JPanel panelCentral = new JPanel(new BorderLayout(5, 5));
        panelCentral.add(panelSensor, BorderLayout.NORTH);

        // --- CONSTRUCCIÓN TERMINAL DE RESPUESTAS (CONSOLA VISUAL) ---
        txtConsola = new JTextArea(8, 30);
        txtConsola.setEditable(false);
        txtConsola.setBackground(Color.BLACK); // Estética de terminal antigua
        txtConsola.setForeground(Color.GREEN);
        panelCentral.add(new JScrollPane(txtConsola), BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // Enlace de oyentes de eventos a los botones de la lógica de control
        btnConectar.addActionListener(e -> conectarAlServidor());
        btnGenerar.addActionListener(e -> generarMedicionOop());
        btnEnviar.addActionListener(e -> enviarMedicion());
    }

    /**
     * Abre el socket de conexión TCP hacia la IP y Puerto especificados en la pantalla.
     */
    private void conectarAlServidor() {
        try {
            String ip = txtIp.getText().trim();
            int puerto = Integer.parseInt(txtPuerto.getText().trim());

            // Intenta levantar la tubería de comunicación de red
            socket = new Socket(ip, puerto);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            txtConsola.append("[RED] Enlace de red establecido con éxito.\n");
            
            // Reajuste de estados de los componentes interactivos de la interfaz
            btnConectar.setEnabled(false);
            btnGenerar.setEnabled(true);
            txtIp.setEditable(false);
            txtPuerto.setEditable(false);

        } catch (Exception ex) {
            txtConsola.append("[ERROR-RED] Error de enlace: " + ex.getMessage() + "\n");
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor. Valida que esté iniciado.", "Fallo de Conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Aplica HERENCIA y POLIMORFISMO para crear la instancia específica del sensor elegido, 
     * generando de forma encapsulada un objeto del tipo 'Medicion'.
     */
    private void generarMedicionOop() {
        String id = txtIdSensor.getText().trim();
        String tipo = (String) cbTipoSensor.getSelectedItem();
        
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe asignar un ID al nodo sensor antes de continuar.");
            return;
        }

        // Variable polimórfica de la superclase abstracta
        SensorSimulado sensor = null;
        
        // Inicialización dinámica según la selección del operador (Polimorfismo de asignación)
        switch (tipo) {
            case "TEMPERATURA":
                sensor = new SensorTemperatura(id); // Instanciación de clase hija
                break;
            case "VOLTAJE":
                sensor = new SensorVoltaje(id);     // Instanciación de clase hija
                break;
            case "DISTANCIA":
                sensor = new SensorDistancia(id);   // Instanciación de clase hija
                break;
        }

        if (sensor != null) {
            // El objeto sensor ejecuta internamente su comportamiento polimórfico y genera la medición
            medicionActual = sensor.generarMedicion();
            
            // Visualización de los datos calculados en los controles de pantalla
            txtValorSimulado.setText(medicionActual.valorComoTexto() + " " + medicionActual.getUnidad());
            txtConsola.append("[POO] Instancia 'Medicion' construida en RAM.\n      Valor simulado: " 
                    + txtValorSimulado.getText() + " | Estado local estimado: " + medicionActual.getEstado() + "\n");
            btnEnviar.setEnabled(true); // Habilita el botón de transmisión por la red
        }
    }

    /**
     * Extrae de forma orientada a objetos la cadena formateada de transmisión 
     * del propio objeto 'Medicion' y la escribe sobre el socket de red TCP.
     */
    private void enviarMedicion() {
        if (medicionActual == null) return; // Validación de seguridad en caso de nulos

        try {
            // Obtiene la trama de texto pura estructurada usando el método encapsulado del objeto
            String mensaje = medicionActual.toMensajeSocket();
            txtConsola.append("[SOCKET ->] Transmitiendo trama: " + mensaje + "\n");
            salida.println(mensaje); // Envía la línea al servidor

            // Captura la respuesta de retroalimentación proveniente de la estación central
            String respuesta = entrada.readLine();
            txtConsola.append("[SERVIDOR <-] Confirmación: " + respuesta + "\n");

            btnEnviar.setEnabled(false); // Fuerza al operador a generar otra medición diferente antes de retransmitir

        } catch (Exception ex) {
            txtConsola.append("[ERROR-RED] Error crítico de flujo de salida: " + ex.getMessage() + "\n");
            // Restablece los controles de conexión en caso de desconexión del servidor central
            btnEnviar.setEnabled(false);
            btnGenerar.setEnabled(false);
            btnConectar.setEnabled(true);
        }
    }
}