package Vista;

import Modelo.Track;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class VistaMP3 extends JFrame {
    // Definición global de componentes interactivos y etiquetas informativas
    public JLabel lblCancion, lblArtista, lblTiempo, lblUsuarioActual;
    public JProgressBar barraProgreso;
    public JButton btnPlay, btnNext, btnPrev, btnAgregar;
    public JTextField txtNuevaCancion, txtNuevoArtista, txtNuevaDuracion;

    // Paleta cromática fija inspirada en aplicaciones modernas de streaming
    private final Color fondoOscuro = new Color(24, 24, 24);
    private final Color fondoControles = new Color(32, 32, 32);
    private final Color textoPrincipal = new Color(255, 255, 255);
    private final Color textoSecundario = new Color(179, 179, 179);
    private final Color acentoGris = new Color(83, 83, 83);

    public VistaMP3() {
        setTitle("Reproductor MP3");
        setSize(420, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(fondoOscuro);
        setLayout(new BorderLayout(20, 20));

        // MARCO SUPERIOR (Norte)
        lblUsuarioActual = new JLabel("  CUENTA: ", SwingConstants.LEFT);
        lblUsuarioActual.setForeground(textoSecundario);
        lblUsuarioActual.setFont(new Font("Segoe UI", Font.BOLD, 10));
        add(lblUsuarioActual, BorderLayout.NORTH);

        // MARCO CENTRAL (Centro)
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(fondoOscuro);
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(40, 30, 20, 30));

        lblCancion = new JLabel("Sin Canción");
        lblCancion.setForeground(textoPrincipal);
        lblCancion.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblCancion.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblArtista = new JLabel("No hay pistas");
        lblArtista.setForeground(textoSecundario);
        lblArtista.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblArtista.setAlignmentX(Component.CENTER_ALIGNMENT);

        barraProgreso = new JProgressBar();
        barraProgreso.setBackground(acentoGris);
        barraProgreso.setForeground(textoPrincipal);
        barraProgreso.setBorderPainted(false);
        barraProgreso.setPreferredSize(new Dimension(300, 4));
        barraProgreso.setMaximumSize(new Dimension(350, 4));
        barraProgreso.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblTiempo = new JLabel("0:00 / 0:00");
        lblTiempo.setForeground(textoSecundario);
        lblTiempo.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lblTiempo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelCentral.add(lblCancion);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 8)));
        panelCentral.add(lblArtista);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 45)));
        panelCentral.add(barraProgreso);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(lblTiempo);
        add(panelCentral, BorderLayout.CENTER);

        // MARCO INFERIOR (Sur)
        JPanel panelInferior = new JPanel(new GridLayout(2, 1, 10, 10));
        panelInferior.setBackground(fondoOscuro);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        panelBotones.setBackground(fondoOscuro);

        btnPrev = crearBotonMultimedia("<<", 16, Color.BLUE);
        btnPlay = crearBotonMultimedia("Play", 16, Color.GREEN);
        btnNext = crearBotonMultimedia(">>", 16, Color.BLUE);

        panelBotones.add(btnPrev);
        panelBotones.add(btnPlay);
        panelBotones.add(btnNext);

        JPanel formularioAgregar = new JPanel(new GridLayout(1, 4, 5, 5));
        formularioAgregar.setBackground(fondoControles);
        formularioAgregar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        txtNuevaCancion = crearCampoTexto("Canción", fondoOscuro, textoPrincipal);
        txtNuevoArtista = crearCampoTexto("Artista", fondoOscuro, textoPrincipal);
        txtNuevaDuracion = crearCampoTexto("Segundos", fondoOscuro, textoPrincipal);

        btnAgregar = new JButton("+");
        btnAgregar.setBackground(textoPrincipal);
        btnAgregar.setForeground(fondoOscuro);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAgregar.setFocusPainted(false);

        formularioAgregar.add(txtNuevaCancion);
        formularioAgregar.add(txtNuevoArtista);
        formularioAgregar.add(txtNuevaDuracion);
        formularioAgregar.add(btnAgregar);

        panelInferior.add(panelBotones);
        panelInferior.add(formularioAgregar);
        add(panelInferior, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private JButton crearBotonMultimedia(String texto, int tamanoFuente, Color colorPresionado) {
        JButton btn = new JButton(texto);
        btn.setBackground(fondoOscuro);
        btn.setForeground(textoPrincipal);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, tamanoFuente));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btn.setForeground(colorPresionado);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (btn != btnPlay || btn.getText().equals("Pausa")) {
                    btn.setForeground(textoPrincipal);
                }
            }
        });

        return btn;
    }

    private JTextField crearCampoTexto(String placeholder, Color fondo, Color textoColor) {
        JTextField txt = new JTextField(placeholder);
        txt.setBackground(fondo);
        txt.setForeground(textoColor);
        txt.setCaretColor(textoColor);
        txt.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 1));
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txt.setHorizontalAlignment(JTextField.CENTER);
        return txt;
    }

    public void actualizar(Track t, int actual, boolean reproduciendo) {
        if (t != null) {
            lblCancion.setText(t.getNombre());
            lblArtista.setText(t.getArtista());
            barraProgreso.setMaximum(t.getDuracionSegundos());
            barraProgreso.setValue(actual);
            lblTiempo.setText(formato(actual) + "  /  " + formato(t.getDuracionSegundos()));

            if (reproduciendo) {
                btnPlay.setText("Pausa");
                btnPlay.setForeground(textoPrincipal);
            } else {
                btnPlay.setText("Play");
                btnPlay.setForeground(Color.RED);
            }
        }
    }

    private String formato(int s) {
        return String.format("%d:%02d", s / 60, s % 60);
    }
}