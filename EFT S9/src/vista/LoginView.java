package vista;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;

    public LoginView() {
        setTitle("Biblioteca Escolar - Iniciar Sesión");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 30, 80, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(110, 30, 180, 25);
        add(txtCorreo);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(30, 80, 80, 25);
        add(lblPass);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(110, 80, 180, 25);
        add(txtContrasena);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(110, 130, 100, 30);
        add(btnIngresar);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = txtCorreo.getText();
                String contraseña = new String(txtContrasena.getPassword());

                UsuarioDAO dao = new UsuarioDAO();
                Usuario usuario = dao.validarLogin(correo, contraseña);

                if (usuario != null) {
                    JOptionPane.showMessageDialog(null, "¡Bienvenido, " + usuario.getNombre() + " (" + usuario.getRol() + ")!");
                    dispose(); // Cierra la ventana de login
                    new MainView(usuario).setVisible(true); // Abre la ventana principal
                } else {
                    JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}