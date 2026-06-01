package Modelo;

/**
 * Clase hija que representa un sensor de voltaje o nivel de batería (Herencia).
 */
public class SensorVoltaje extends SensorSimulado {

    // Constructor para inicializar el módulo con el tipo y unidad correspondientes
    public SensorVoltaje(String id) {
        super(id, "VOLTAJE", "V");
    }

    // Sobrescritura para simular el voltaje de una celda/batería entre 9.0V y 12.6V
    @Override
    protected double generarValor() {
        return aleatorio(9.0, 12.6);
    }

    // Sobrescritura que dispara estado de advertencia si el voltaje cae por debajo de 10.5V
    @Override
    public String evaluarEstado(double valor) {
        return (valor < 10.5) ? "BATERIA_BAJA" : "NORMAL";
    }
}