package Modelo;

import java.io.*;
import java.util.*;

public class RepositorioMusica {
    // Nombre del archivo de texto plano centralizado que almacena las cuentas de usuario
    private final String RUTA_USUARIOS = "usuarios.txt";

    // Carga en memoria la lista de pistas asociadas al archivo .txt particular de un usuario
    public List<Track> leerPlaylistUsuario(String username) {
        List<Track> lista = new ArrayList<>();
        String archivoUsuario = "playlist_" + username + ".txt";
        File archivo = new File(archivoUsuario);

        // Si el usuario no posee un archivo de playlist, genera uno por defecto con canciones base
        if (!archivo.exists()) {
            crearPlaylistBase(archivoUsuario, username);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuario))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(","); // Divide la línea usando la coma como delimitador
                if (partes.length == 3) {
                    lista.add(new Track(partes[0].trim(), partes[1].trim(), Integer.parseInt(partes[2].trim())));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer la playlist de: " + username);
        }
        return lista;
    }

    // Añade una nueva línea con los datos de la canción al archivo del usuario (Modo Append)
    public void guardarCancionUsuario(String username, Track nuevaCancion) {
        String archivoUsuario = "playlist_" + username + ".txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoUsuario, true))) {
            pw.println(nuevaCancion.getNombre() + ", " + nuevaCancion.getArtista() + ", "
                    + nuevaCancion.getDuracionSegundos());
        } catch (IOException e) {
            System.err.println("Error al guardar cancion para: " + username);
        }
    }

    // Lee y compila todos los usuarios guardados en usuarios.txt al arrancar la aplicación
    public List<Usuario> leerUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        File archivo = new File(RUTA_USUARIOS);
        if (!archivo.exists()) {
            crearUsuariosBase(); // Genera credenciales predeterminadas si el archivo no existe
        }

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    lista.add(new Usuario(partes[0].trim(), partes[1].trim()));
                }
            }
        } catch (Exception e) {
            System.err.println("Error critico al leer el archivo de usuarios.");
        }
        return lista;
    }

    // Crea un archivo inicial con dos audios genéricos de muestra personalizados con el nombre del usuario
    private void crearPlaylistBase(String ruta, String username) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            pw.println("Audio Recording Title, " + username + " Podcast, 120");
            pw.println("Segunda Cancion, " + username + " Artista, 215");
        } catch (IOException e) {
            System.err.println("No se pudo crear la playlist base.");
        }
    }

    // Establece dos cuentas por defecto iniciales dentro del archivo general de usuarios
    private void crearUsuariosBase() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA_USUARIOS))) {
            pw.println("admin, 1234");
            pw.println("user, java2026");
        } catch (IOException e) {
            System.err.println("No se pudo crear la base de usuarios.");
        }
    }
}