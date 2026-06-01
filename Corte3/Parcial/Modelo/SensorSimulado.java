package Modelo;

import java.util.Random; // Importa la clase utilitaria para generación de números aleatorios

/**
 * Clase Abstracta que sirve como molde base (Abstracción) para cualquier tipo de sensor.
 * No se puede instanciar directamente, define el comportamiento general de un dispositivo de telemetría.
 */
public abstract class SensorSimulado {
    // Atributos encapsulados comunes para todas las subclases
    private String id;
    private String tipo;
    private String unidad;
    private Random random; // Objeto generador de datos aleatorios

    // Constructor de la superclase que será invocado por las clases hijas usando 'super'
    public SensorSimulado(String id, String tipo, String unidad) {
        this.id = id;
        this.tipo = tipo;
        this.unidad = unidad;
        this.random = new Random(); // Instanciación del generador aleatorio
    }

    // Getters para permitir la lectura externa de los atributos protegidos
    public String getId() { return id; }
    public String getTipo() { return tipo; }
    public String getUnidad() { return unidad; }

    /**
     * Método utilitario interno para calcular un número decimal aleatorio dentro de un rango.
     */
    protected double aleatorio(double minimo, double maximo) {
        return minimo + (maximo - minimo) * random.nextDouble();
    }

    /**
     * Método plantilla que coordina la creación de un objeto Medicion estructurado.
     * Invoca de forma polimórfica a los métodos abstractos implementados en los hijos.
     */
    public Medicion generarMedicion() {
        double valor = generarValor();        // Llama al método específico del hijo en tiempo de ejecución
        String estado = evaluarEstado(valor); // Llama a las reglas específicas de alerta del hijo
        return new Medicion(id, tipo, valor, unidad, estado); // Retorna el objeto unificado
    }

    // Métodos Abstractos: obligan a las clases hijas a definir sus propios rangos y reglas de alerta
    protected abstract double generarValor();
    public abstract String evaluarEstado(double valor);
}