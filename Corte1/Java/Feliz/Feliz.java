public class Feliz {
    //Atributos o campos
    String nombre;
    int edad;
    int peso;
    //Constructores
    public Feliz(String nombre) {
        this.nombre = nombre;
    }
    public Feliz(int edad, int peso) {
        this.edad = edad;
        this.peso = peso;
    }

    //metodos
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
