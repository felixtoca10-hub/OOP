package Modelo;

import java.util.List;

public class SimuladorLogica {
    // Referencia a la lista de canciones en memoria y variables de estado del reproductor
    private final List<Track> canciones;
    private int indice = 0;              // Posición de la canción actual en la lista
    private int segundoActual = 0;       // Contador del tiempo transcurrido en la reproducción
    private boolean activo = false;      // Estado del reproductor (true = Play, false = Pausa)

    // Constructor que asigna la playlist cargada desde el almacenamiento
    public SimuladorLogica(List<Track> lista) {
        this.canciones = lista;
    }

    // Método encargado de simular el paso del tiempo, invocado de forma periódica cada segundo
    public void tick() {
        if (activo && !canciones.isEmpty()) {
            if (segundoActual < canciones.get(indice).getDuracionSegundos()) {
                segundoActual++;
            } else {
                // Al llegar al final de la pista, salta automáticamente a la siguiente canción
                siguiente();
            }
        }
    }

    // Inserta dinámicamente un objeto Track a la colección enlazada en ejecución
    public void agregarCancionALista(Track track) {
        this.canciones.add(track);
    }

    // Cambia el estado de reproducción (Play/Pausa)
    public void alternarEstado() {
        if (!canciones.isEmpty())
            activo = !activo;
    }

    // Avanza a la siguiente pista de la lista usando aritmética modular
    public void siguiente() {
        indice = (indice + 1) % canciones.size();
        segundoActual = 0; // Reinicia el contador de tiempo para la nueva canción
    }

    // Retrocede a la pista anterior manejando los límites inferiores
    public void anterior() {
        indice = (indice - 1 + canciones.size()) % canciones.size();
        segundoActual = 0; // Reinicia el contador de tiempo para la nueva canción
    }

    // Retorna el objeto de la canción actual o null si la lista está vacía
    public Track getActual() {
        return canciones.isEmpty() ? null : canciones.get(indice);
    }

    // Métodos consultores (Getters) de las variables de estado internas
    public int getSegundoActual() {
        return segundoActual;
    }

    public boolean isActivo() {
        return activo;
    }
}