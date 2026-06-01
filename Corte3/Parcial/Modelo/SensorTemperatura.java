package Modelo;

/**
 * Clase hija que representa un sensor de temperatura específico (Herencia).
 */
public class SensorTemperatura extends SensorSimulado {

    // Constructor que inicializa los valores fijos para el tipo de entorno de temperatura
    public SensorTemperatura(String id) {
        super(id, "TEMPERATURA", "C"); // Llama al constructor de la clase padre
    }

    // Sobrescritura (Polimorfismo) para simular valores de temperatura entre 20.0°C y 90.0°C
    @Override
    protected double generarValor() {
        return aleatorio(20.0, 90.0);
    }

    // Sobrescritura (Polimorfismo) que define el umbral crítico si supera o es igual a 70.0°C
    @Override
    public String evaluarEstado(double valor) {
        return (valor >= 70.0) ? "ALERTA" : "NORMAL";
    }
}