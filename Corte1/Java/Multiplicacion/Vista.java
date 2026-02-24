public class Vista {

    public Vista() {        
    }

    public void mostrarTitulo(){
         System.out.println("Programa multiplicar dos Numero Enteros:");
    }

    public void mostrarNumero(){
        System.out.println("Digite numero:");
    }

    public void mostrarResultado(){
        System.out.println("El resultado es:");
    }

    public void mostrarSalida(Numero multiplicacion){
        System.out.println(multiplicacion.getNumero());
    }

}
