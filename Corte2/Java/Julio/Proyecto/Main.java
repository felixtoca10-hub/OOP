import Controlador.ControladorMP3;
import Modelo.RepositorioMusica;
import Vista.VistaLogin;
import Vista.VistaMP3;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater asegura que la creación de la GUI
        // se realice de forma segura en el hilo de eventos de Swing.
        SwingUtilities.invokeLater(() -> {
            // Inicializar persistencia de datos (Modelo)
            RepositorioMusica repo = new RepositorioMusica();

            // Construir interfaces gráficas independientes (Vista)
            VistaLogin login = new VistaLogin();
            VistaMP3 mp3 = new VistaMP3();

            // Unir componentes mediante el Controlador, pasando los usuarios iniciales
            ControladorMP3 controlador = new ControladorMP3(login, mp3, repo, repo.leerUsuarios());

            // Mostrar Login al usuario
            login.setVisible(true);
        });
    }
}