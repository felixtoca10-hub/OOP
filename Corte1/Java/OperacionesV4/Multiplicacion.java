public class Multiplicacion extends Operacion {
    public Multiplicacion(Numero numero1, Numero n2) { 
        super(numero1, n2); 
    }

    @Override
    public Numero calcular() {
        int resultado = numero1.getNumero() * numero2.getNumero();
        return new Numero(resultado);
    }
}