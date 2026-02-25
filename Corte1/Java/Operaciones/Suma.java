public class Suma {
    //Atributos
    private Numero numero1;
    private Numero numero2;

    public Suma(Numero numero1, Numero numero2){
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public Numero sumar(){
        int resultado = numero1.getNumero() + numero2.getNumero();
        return new Numero(resultado);
    }
    
}
