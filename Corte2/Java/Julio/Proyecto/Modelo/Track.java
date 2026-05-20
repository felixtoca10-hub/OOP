package Modelo;

public class Track {
    // Atributos que representan las propiedades de una pista de audio
    private final String nombre;
    private final String artista;
    private final int duracionSegundos;

    // Constructor que asigna los valores de metadatos al instanciar una canción
    public Track(String nombre, String artista, int duracionSegundos) {
        this.nombre = nombre;
        this.artista = artista;
        this.duracionSegundos = duracionSegundos;
    }

    // Métodos consultores (Getters) públicos
    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }
}