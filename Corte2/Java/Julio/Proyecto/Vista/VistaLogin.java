package Vista;

import java.awt.*;
import javax.swing.*;

public class VistaLogin extends JFrame {
    // Componentes interactivos de la UI (se eliminó el botón de registrar)
    public JTextField txtUsuario;
    public JPasswordField txtPassword;
    public JButton btnIngresar;

    public VistaLogin() {
        // Ajustes iniciales del contenedor gráfico principal de inicio de sesión
        setTitle("Acceso MP3");
        setSize(320, 220); // Ajuste leve de tamaño vertical ya que hay un componente menos
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Definición de paleta cromática en Modo Oscuro
        Color fondoOscuro = new Color(24, 24, 24);
        Color fondoCampos = new Color(40, 40, 40);
        Color textoPrincipal = new Color(255, 255, 255);

        // Aplica el fondo oscuro al panel base
        getContentPane().setBackground(fondoOscuro);

        // Diseña una rejilla tabular (GridLayout) ajustada a 3 filas para optimizar la distribución
        JPanel panelContenedor = new JPanel(new GridLayout(3, 2, 10, 15));
        panelContenedor.setBackground(fondoOscuro);
        panelContenedor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiqueta y caja de texto para el usuario
        JLabel lblUsuario = new JLabel("Usuario:", SwingConstants.CENTER);
        lblUsuario.setForeground(textoPrincipal);
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));

        txtUsuario = new JTextField();
        txtUsuario.setBackground(fondoCampos);
        txtUsuario.setForeground(textoPrincipal);
        txtUsuario.setCaretColor(textoPrincipal);
        txtUsuario.setHorizontalAlignment(JTextField.CENTER);
        txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));

        // Etiqueta y caja para la contraseña
        JLabel lblPassword = new JLabel("Contraseña:", SwingConstants.CENTER);
        lblPassword.setForeground(textoPrincipal);
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));

        txtPassword = new JPasswordField();
        txtPassword.setBackground(fondoCampos);
        txtPassword.setForeground(textoPrincipal);
        txtPassword.setCaretColor(textoPrincipal);
        txtPassword.setHorizontalAlignment(JTextField.CENTER);
        txtPassword.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));

        // Configuración estética del botón de ingreso principal
        btnIngresar = new JButton("Iniciar Sesión");
        btnIngresar.setBackground(textoPrincipal);
        btnIngresar.setForeground(fondoOscuro);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Incorporación ordenada de los elementos dentro de las celdas
        panelContenedor.add(lblUsuario);
        panelContenedor.add(txtUsuario);
        panelContenedor.add(lblPassword);
        panelContenedor.add(txtPassword);

        // Se coloca un JLabel vacío a la izquierda para empujar el botón Ingresar a la derecha
        panelContenedor.add(new JLabel());
        panelContenedor.add(btnIngresar);

        // Añade el panel configurado a la ventana y establece su centrado
        add(panelContenedor);
        setLocationRelativeTo(null);
    }
}