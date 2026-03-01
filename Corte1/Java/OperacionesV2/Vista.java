public class Vista {

    public void escribirNumero(){
        System.out.println("Escriba un numero entero: ");
    }
    
    public void mostrarTitulo(){
        System.out.println("===============================================");
        System.out.println("Programa de operaciones de dos numeros enteros: ");
        System.out.println("===============================================");
    }

    public void escribirSalidaS(Numero resultado1){
        System.out.println("La suma es: " + resultado1.getNumero());
    }
    
    public void escribirSalidaR(Numero resultado1){
        System.out.println("La resta es: " + resultado1.getNumero());
    }
    
    public void escribirSalidaM(Numero resultado1){
        System.out.println("La multiplicacion es: " + resultado1.getNumero());
    }
    
    public void escribirSalidaD(Numero resultado1){
        System.out.println("La division es: " + resultado1.getNumero());
    }

    public void mostrarMenu(){
        System.out.println("\n--- Menú de Operaciones ---\n");
        System.out.println("1. Realizar una suma");
        System.out.println("2. Realizar una resta");
        System.out.println("3. Realizar una multiplicacion");
        System.out.println("4. Realizar una division");
        System.out.println("5. Salir");
        System.out.println("Seleccione una opción");
    }

    public void salirPrograma(){
        System.out.println("Saliendo de la aplicación...");
    }

    public void mostrarDefault(){
        System.out.println("Opción no valida. Por favor, intente de nuevo. Solo 1, 2, 3, 4 o 5");
    }
}