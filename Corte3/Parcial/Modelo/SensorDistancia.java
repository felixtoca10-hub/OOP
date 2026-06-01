package Modelo;

/**
 * Clase hija que representa un sensor de proximidad o ultrasonido (Herencia).
 */
public class SensorDistancia extends SensorSimulado {

    // Constructor de inicialización para variables de distancia lineal
    public SensorDistancia(String id) {
        super(id, "DISTANCIA", "m");
    }

    // Sobrescritura para generar lecturas operativas de distancia entre 0.10m y 2.00m
    @Override
    protected double generarValor() {
        return aleatorio(0.10, 2.00);
    }

    // Sobrescritura que advierte peligro de colisión si un objeto está a menos de 0.40m
    @Override
    public String evaluarEstado(double valor) {
        return (valor < 0.40) ? "OBSTACULO_CERCANO" : "NORMAL";
    }
}