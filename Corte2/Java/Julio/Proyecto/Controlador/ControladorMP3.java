package Controlador;

import Modelo.RepositorioMusica;
import Modelo.SimuladorLogica;
import Modelo.Track;
import Modelo.Usuario;
import Vista.VistaLogin;
import Vista.VistaMP3;
import java.util.List;
import javax.swing.*;

public class ControladorMP3 {
    // Atributos de enlace que comunican las vistas con el modelo
    private final VistaLogin vistaLogin;
    private final VistaMP3 vistaMP3;
    private final RepositorioMusica repo;
    private final List<Usuario> usuariosValidos;
    private SimuladorLogica logica; 
    private String usuarioLogueado;

    // El constructor asocia únicamente los listeners del flujo de validación de entrada (sin botón de registro)
    public ControladorMP3(VistaLogin login, VistaMP3 mp3, RepositorioMusica repo, List<Usuario> usuarios) {
        this.vistaLogin = login;
        this.vistaMP3 = mp3;
        this.repo = repo;
        this.usuariosValidos = usuarios;

        // Inyección de escuchador al botón de ingresar usando expresiones lambda
        this.vistaLogin.btnIngresar.addActionListener(e -> intentarLogin());
    }

    // Gestiona la comprobación para la validación del acceso al sistema
    private void intentarLogin() {
        String userInput = vistaLogin.txtUsuario.getText();
        String passInput = new String(vistaLogin.txtPassword.getPassword());
        boolean accesoConcedido = false;

        // Recorrido secuencial iterativo sobre la lista de cuentas guardadas
        for (Usuario u : usuariosValidos) {
            if (u.validar(userInput, passInput)) {
                accesoConcedido = true;
                usuarioLogueado = u.getUsername();
                break;
            }
        }

        if (accesoConcedido) {
            vistaLogin.dispose(); // Destruye y libera de la memoria el frame de login
            // Inicializa la instancia cargando el archivo de texto exclusivo de la playlist del usuario
            this.logica = new SimuladorLogica(repo.leerPlaylistUsuario(usuarioLogueado));
            iniciarReproductor(); // Inicializa los bucles del reproductor musical principal
        } else {
            // Despliega ventana emergente de error
            JOptionPane.showMessageDialog(vistaLogin, "Credenciales Incorrectas", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Acopla los triggers multimedia y levanta el bucle de temporización cíclico de la pantalla principal
    private void iniciarReproductor() {
        vistaMP3.lblUsuarioActual.setText("  CUENTA: " + usuarioLogueado.toUpperCase());

        // Hilo cíclico periódico que corre indefinidamente cada segundo
        Timer cronometro = new Timer(1000, e -> {
            logica.tick(); 
            vistaMP3.actualizar(logica.getActual(), logica.getSegundoActual(), logica.isActivo());
        });
        cronometro.start(); 

        // Enlace de los disparadores para los controles de reproducción táctiles del MP3
        vistaMP3.btnPlay.addActionListener(e -> {
            logica.alternarEstado();
            vistaMP3.actualizar(logica.getActual(), logica.getSegundoActual(), logica.isActivo());
        });

        vistaMP3.btnNext.addActionListener(e -> {
            logica.siguiente();
            vistaMP3.actualizar(logica.getActual(), logica.getSegundoActual(), logica.isActivo());
        });

        vistaMP3.btnPrev.addActionListener(e -> {
            logica.anterior();
            vistaMP3.actualizar(logica.getActual(), logica.getSegundoActual(), logica.isActivo());
        });

        // Evento asignado al botón "+" encargado de capturar, validar e insertar nuevas canciones al archivo
        vistaMP3.btnAgregar.addActionListener(e -> {
            try {
                String nombre = vistaMP3.txtNuevaCancion.getText();
                String artista = vistaMP3.txtNuevoArtista.getText();
                int duracion = Integer.parseInt(vistaMP3.txtNuevaDuracion.getText()); 

                // Instancia el objeto Track, guarda el registro físico (.txt) e inyecta la pista en la caché de la lista
                Track nuevaCancion = new Track(nombre, artista, duracion);
                repo.guardarCancionUsuario(usuarioLogueado, nuevaCancion);
                logica.agregarCancionALista(nuevaCancion);

                JOptionPane.showMessageDialog(vistaMP3, "¡Cancion agregada a tu playlist!");

                // Restaura los placeholders vaciando los campos gráficos de inserción
                vistaMP3.txtNuevaCancion.setText("");
                vistaMP3.txtNuevoArtista.setText("");
                vistaMP3.txtNuevaDuracion.setText("");
            } catch (NumberFormatException ex) {
                // Captura el error en caso de que el usuario introduzca letras dentro de la entrada numérica de segundos
                JOptionPane.showMessageDialog(vistaMP3, "La duracion debe escribirse en segundos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Sincroniza el dibujo de arranque inicial y despliega visiblemente el reproductor multimedia final
        vistaMP3.actualizar(logica.getActual(), logica.getSegundoActual(), logica.isActivo());
        vistaMP3.setVisible(true);
    }
}