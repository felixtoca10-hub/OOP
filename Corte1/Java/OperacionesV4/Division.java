public class Division extends Operacion {
    public Division(Numero numero1, Numero numero2) { 
        super(numero1, numero2); 
    }

    @Override
    public Numero calcular() {
        if (numero2.getNumero() == 0) {
            System.out.println("Error: No se puede dividir por cero.");
            return new Numero(0);
        }
        int resultado = numero1.getNumero() / numero2.getNumero();
        return new Numero(resultado);
    }
}