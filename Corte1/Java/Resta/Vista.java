public class Vista {

    public Vista() {        
    }

    public void mostrarTitulo(){
         System.out.println("Programa restar dos Numero Enteros:");
    }

    public void mostrarNumero(){
        System.out.println("Digite numero:");
    }

    public void mostrarResultado(){
        System.out.println("El resultado es:");
    }

    public void mostrarSalida(Numero resta){
        System.out.println(resta.getNumero());
    }

}
