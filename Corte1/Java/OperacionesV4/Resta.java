public class Resta extends Operacion {
    public Resta(Numero numero1, Numero numero2) { 
        super(numero1, numero2); 
    }

    @Override
    public Numero calcular() {
        int resultado = numero1.getNumero() - numero2.getNumero();
        return new Numero(resultado);
    }
}