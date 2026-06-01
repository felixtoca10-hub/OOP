package Servidor;

import Modelo.Medicion;
import Modelo.SensorDistancia;
import Modelo.SensorTemperatura;
import Modelo.SensorVoltaje;
import Persistencia.ManejadorCSV;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Interfaz gráfica del Servidor Centralizado. Controla los hilos de red y visualiza los datos en una JTable.
 */
public class VentanaServidor extends JFrame {
    // Componentes visuales de la interfaz Swing
    private JTextField txtPuerto;
    private JButton btnIniciar;
    private JTable tablaMediciones;
    private DefaultTableModel modeloTabla;
    private JLabel lblEstado;
    private JTextArea txtLog;
    
    // Objetos de control para la infraestructura de red y persistencia
    private ServerSocket serverSocket;
    private boolean corriendo = false;
    private ManejadorCSV manejadorCSV;

    // Constructor: Inicializa la interfaz y enlaza el manejador de archivos
    public VentanaServidor() {
        setTitle("Servidor de Monitoreo Centralizado - Ingeniería");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(new BorderLayout(10, 10)); // Distribución espacial con márgenes de 10px

        // Instancia la capa de persistencia apuntando al archivo CSV obligatorio
        manejadorCSV = new ManejadorCSV("mediciones.csv");

        // --- CONSTRUCCIÓN PANEL SUPERIOR (CONFIGURACIÓN) ---
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Puerto de Escucha:"));
        txtPuerto = new JTextField("5000", 6); // Puerto por defecto sugerido
        panelSuperior.add(txtPuerto);
        btnIniciar = new JButton("Iniciar Servidor");
        panelSuperior.add(btnIniciar);
        lblEstado = new JLabel("Estado: Apagado");
        lblEstado.setForeground(Color.RED);
        panelSuperior.add(lblEstado);
        add(panelSuperior, BorderLayout.NORTH);

        // --- CONSTRUCCIÓN PANEL CENTRAL (TABLA DE DATOS) ---
        String[] columnas = {"Fecha", "Hora", "ID Sensor", "Tipo Sensor", "Valor", "Unidad", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0); // Tabla vacía inicialmente
        tablaMediciones = new JTable(modeloTabla);
        add(new JScrollPane(tablaMediciones), BorderLayout.CENTER); // JScrollPane añade barras de desplazamiento

        // --- CONSTRUCCIÓN PANEL INFERIOR (CONSOLA DE EVENTOS LOGS) ---
        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        txtLog = new JTextArea(6, 40);
        txtLog.setEditable(false); // Evita que el usuario borre los logs de forma manual
        panelInferior.add(new JScrollPane(txtLog), BorderLayout.CENTER);
        
        JLabel lblArchivo = new JLabel(" 💾 Archivo permanente de salida: mediciones.csv (Ruta Relativa)");
        lblArchivo.setFont(new Font("Monospaced", Font.BOLD, 12));
        panelInferior.add(lblArchivo, BorderLayout.SOUTH);
        add(panelInferior, BorderLayout.SOUTH);

        // Enlace del evento clic del botón para arrancar los sockets
        btnIniciar.addActionListener(e -> iniciarServidor());
    }

    /**
     * Inicializa el socket del servidor en el puerto digitado.
     * Crea un hilo principal para no congelar la pantalla mientras espera conexiones.
     */
    private void iniciarServidor() {
        try {
            int puerto = Integer.parseInt(txtPuerto.getText().trim());
            serverSocket = new ServerSocket(puerto); // Abre el puerto de red
            corriendo = true;
            
            // Actualización visual de estado en línea
            lblEstado.setText("Escuchando en puerto: " + puerto);
            lblEstado.setForeground(new Color(0, 128, 0)); // Color verde
            btnIniciar.setEnabled(false);
            txtPuerto.setEditable(false);
            log("Servidor TCP en línea e inicializado.");

            // HILO PRINCIPAL DE RED: Se ejecuta en paralelo para escuchar clientes sin bloquear la GUI
            new Thread(() -> {
                while (corriendo) {
                    try {
                        Socket clienteSocket = serverSocket.accept(); // Espera bloqueante a que se conecte un cliente
                        log("Cliente aceptado desde la dirección: " + clienteSocket.getRemoteSocketAddress());
                        
                        // CONCURRENCIA: Delega el cliente a un hilo independiente y sigue escuchando más peticiones
                        new HiloCliente(clienteSocket).start();
                    } catch (IOException e) {
                        if (!serverSocket.isClosed()) {
                            log("Error en la aceptación de flujo: " + e.getMessage());
                        }
                    }
                }
            }).start();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El puerto debe ser un valor numérico entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error de red: El puerto ya está ocupado por otra aplicación.", "Fallo de Socket", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Escribe un mensaje con salto de línea en la consola interna de logs de la pantalla.
     */
    public synchronized void log(String mensaje) {
        txtLog.append(mensaje + "\n");
        txtLog.setCaretPosition(txtLog.getDocument().getLength()); // Hace scroll automático hacia abajo
    }

    /**
     * CLASE INTERNA DE HILO (Threads): Ejecuta una tarea independiente para atender 
     * a cada módulo de medición de manera concurrente y asíncrona.
     */
    private class HiloCliente extends Thread {
        private Socket socket; // Socket exclusivo de comunicación con este cliente específico

        public HiloCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            // Inicializa flujos de lectura y escritura de texto estructurado sobre el socket
            try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                String linea;
                // Escucha de forma permanente los mensajes enviados por este cliente específico
                while ((linea = entrada.readLine()) != null) {
                    log("Mensaje crudo entrante por red: " + linea);
                    
                    // Tokeniza la cadena separada por puntos y comas (Formato establecido)
                    String[] partes = linea.split(";");
                    if (partes.length != 4) {
                        salida.println("ERROR;Formato incompleto de trama de datos");
                        continue;
                    }

                    // Extracción de variables limpiando espacios en blanco
                    String id = partes[0].trim();
                    String tipo = partes[1].trim().toUpperCase();
                    String valorTexto = partes[2].trim();
                    String unidad = partes[3].trim();

                    try {
                        double valor = Double.parseDouble(valorTexto);
                        
                        // POLIMORFISMO EN ACCIÓN: Se aplican las reglas específicas del tipo de sensor dinámicamente
                        String estadoCalculado = "NORMAL";
                        if (tipo.equals("TEMPERATURA")) {
                            estadoCalculado = new SensorTemperatura(id).evaluarEstado(valor);
                        } else if (tipo.equals("VOLTAJE")) {
                            estadoCalculado = new SensorVoltaje(id).evaluarEstado(valor);
                        } else if (tipo.equals("DISTANCIA")) {
                            estadoCalculado = new SensorDistancia(id).evaluarEstado(valor);
                        } else {
                            salida.println("ERROR;Módulo de sensado desconocido en la central");
                            continue;
                        }

                        // Reconstrucción orientada a objetos de la información recibida
                        Medicion medicion = new Medicion(id, tipo, valor, unidad, estadoCalculado);
                        
                        // Captura de marcas temporales de la máquina del servidor central
                        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());

                        // PERSISTENCIA: Escritura segura y síncrona dentro del archivo CSV físico
                        manejadorCSV.registrarMedicion(medicion, fecha, hora);

                        // ACTUALIZACIÓN GUI SEGURA: Inserta los datos en la tabla gráfica usando el EDT de Swing
                        SwingUtilities.invokeLater(() -> modeloTabla.addRow(new Object[]{
                                fecha, hora, medicion.getIdSensor(), medicion.getTipoSensor(),
                                medicion.valorComoTexto(), medicion.getUnidad(), medicion.getEstado()
                        }));

                        // Envía confirmación de éxito de vuelta al nodo cliente de origen
                        salida.println("OK;Lectura procesada y guardada en base CSV");

                    } catch (NumberFormatException nfe) {
                        salida.println("ERROR;La variable de valor de simulación debe ser numérica");
                    } catch (Exception ex) {
                        salida.println("ERROR;Fallo interno del servidor de almacenamiento");
                        log("Excepción interna al procesar: " + ex.getMessage());
                    }
                }
            } catch (IOException e) {
                log("Un canal cliente cerró la sesión activa.");
            } finally {
                try {
                    socket.close(); // Libera los recursos de red del socket al finalizar
                } catch (IOException e) {
                    log("Error al cerrar el flujo del cliente.");
                }
            }
        }
    }
}