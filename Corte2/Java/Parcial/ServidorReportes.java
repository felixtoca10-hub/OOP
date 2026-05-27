// Archivo: ServidorReportes.java
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

/**
 * Servidor que escucha conexiones de clientes, recibe reportes y los guarda en un archivo de texto plano (.txt).
 */
public class ServidorReportes {
    // Puerto de red donde escuchará el servidor (Debe coincidir con el del cliente)
    private static final int PUERTO = 12345;
    
    // CAMBIO AQUÍ: Ahora la extensión del archivo plano es explícitamente .txt
    private static final String ARCHIVO_TEXTO = "reportes.txt";

    public static void main(String[] args) {
        System.out.println("=== SERVIDOR DE REPORTES INICIADO ===");
        
        // REQUISITO 7: Creación del Socket de Servidor (ServerSocket)
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Escuchando conexiones en el puerto " + PUERTO + "...");

            // Bucle infinito para mantener el servidor activo recibiendo múltiples clientes
            while (true) {
                // El servidor se detiene aquí hasta que un cliente solicita conectarse
                Socket socketCliente = serverSocket.accept();
                System.out.println("Nueva conexión entrante desde: " + socketCliente.getInetAddress());

                // REQUISITO 6: El servidor delega la atención del cliente a un nuevo Hilo independiente.
                new Thread(() -> manejarCliente(socketCliente)).start();
            }
        } catch (IOException e) {
            System.err.println("Error crítico en el servidor: " + e.getMessage());
        }
    }

    /**
     * Método ejecutado dentro de un hilo secundario para procesar la transmisión del cliente.
     */
    private static void manejarCliente(Socket socket) {
        // Inicializar canales de comunicación (Lectura y Escritura) a través del Socket
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Leer secuencialmente las líneas de texto enviadas por el cliente
            String tipoReporte = entrada.readLine();
            String autor = entrada.readLine();
            String descripcion = entrada.readLine();
            String campoExtra = entrada.readLine();
            String fechaActual = LocalDate.now().toString(); // Captura la fecha del sistema

            // REQUISITO 5: Almacenar la información estructurada en el archivo .txt
            guardarEnTXT(fechaActual, tipoReporte, autor, descripcion, campoExtra);

            // REQUISITO 7: Responder al cliente confirmando el éxito de la operación
            salida.println("ÉXITO: Reporte procesado y guardado correctamente en el Servidor.");
            System.out.println("Reporte de '" + autor + "' almacenado con éxito en el archivo de texto.");

        } catch (IOException e) {
            System.err.println("Error al procesar comunicación con cliente: " + e.getMessage());
        } finally {
            // Garantizar el cierre del socket al finalizar la comunicación
            try {
                socket.close();
                System.out.println("Conexión con el cliente cerrada de manera limpia.");
            } catch (IOException e) {
                System.err.println("Error al cerrar socket cliente: " + e.getMessage());
            }
        }
    }

    /**
     * REQUISITO 5: Guarda una línea de texto estructurada en el archivo plano .txt de forma persistente.
     * Se usa 'synchronized' para evitar conflictos de escritura entre múltiples hilos.
     */
    private synchronized static void guardarEnTXT(String fecha, String tipo, String autor, String desc, String extra) {
        // El parámetro 'true' habilita el modo "Append" (añadir al final del archivo sin borrar lo anterior)
        try (FileWriter fw = new FileWriter(ARCHIVO_TEXTO, true)) {
            // Reemplazamos comas dentro de la descripción para mantener una estructura limpia
            String descSegura = desc.replace(",", ";"); 
            
            // Escribir los campos estructurados y finalizados con un salto de línea (\n)
            fw.append(String.format("Fecha: %s | Tipo: %s | Autor: %s | Descripción: %s | Info Extra: %s\n", 
                                    fecha, tipo, autor, descSegura, extra));
        } catch (IOException e) {
            System.err.println("Error de escritura en disco (.txt): " + e.getMessage());
        }
    }
}