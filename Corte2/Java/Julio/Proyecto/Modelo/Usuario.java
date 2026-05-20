package Modelo;

public class Usuario {
    // Atributos privados encapsulados que almacenan las credenciales
    private final String username;
    private final String password;

    // Constructor para inicializar el objeto Usuario
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Métodos consultores (Getters)
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Método booleano encargado de validar si las credenciales coinciden
    public boolean validar(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }
}