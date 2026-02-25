public class Vista {

    public void escribirNumero(){
        System.out.println("Escriba un numero entero: ");
    }

    public void escribirSalida(Numero resultado1){
        System.out.println("La suma es: " + resultado1.getNumero());
    }

    public void mostrarMenu(){
        System.out.println("\n--- Menú de Suma ---");
        System.out.println("1. Realizar una suma");
        System.out.println("2. Salir");
        System.out.println("Seleccione una opción");
    }

    public void salirPrograma(){
        System.out.println("Saliendo de la aplicación...");
    }

    public void mostrarDefault(){
        System.out.println("Opción no valida. Por favor, intente de nuevo. Solo 1 o 2");
    }
}
