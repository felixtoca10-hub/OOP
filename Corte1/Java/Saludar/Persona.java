package persona;

public class Persona {
    public String nombre;
    public int edad;
    
    public void saludar(){
        System.out.println("Hola soy " + nombre + " y tengo " + edad + " años.");
    }

    public static void main(String[] args) {
        Persona p1 = new Persona();
        Persona p2 = new Persona();
        Persona p3 = new Persona();
        
        p1.nombre = "Felix";
        p1.edad = 19;
        p1.saludar();
        
        p2.nombre = "Steven";
        p2.edad = 18;
        p2.saludar();
        
        p3.nombre = "Capi";
        p3.edad = 18;
        p3.saludar();
    }
    
}
