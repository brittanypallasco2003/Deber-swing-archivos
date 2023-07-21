import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login {
    private JPanel rootPanel;
    private JTextField inputUsuario;
    private JButton iniciarSesionButton;
    private JPasswordField inputPassword;
    private JCheckBox seePasswordCheckBox;

    public JPanel getRootPanel() {
        return rootPanel;
    }

    //variable para almacenar datos de la clase RegistroUsuario
    private RegistroUsuario registroUsuario;
    //constructor vacio
    public Login(){}
    public Login(RegistroUsuario registroUsuario) {
        this.registroUsuario = registroUsuario; //elemento del constructor

        //Listener
        seePasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(seePasswordCheckBox.isSelected()){
                    inputPassword.setEchoChar((char)0);//para el caso de mostrar.
                }else {
                    inputPassword.setEchoChar('*');//si el check esta desabilitado se llenara el texfield de *
                    //setea cualquier carácter para ocultar la contraseña
                }
            }
        });
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //recoge datos de la interfaz
                String usuario = inputUsuario.getText();
                String password = String.valueOf(inputPassword.getPassword());

                //verificacion de credenciales
                boolean verificarCredenciales = veficarCredenciales(usuario,password);
                if(verificarCredenciales){
                    //mensaje de bienvenida si las credenciales son correctas
                    JOptionPane.showMessageDialog(null,"Bienvenido "+usuario,"Inicio de sesión exitoso",JOptionPane.INFORMATION_MESSAGE);

                } else{
                    //mensaje de error si usuario o contraseña incorrectos
                    JOptionPane.showMessageDialog(null,"Usuario o constraseña incorrectos!","Error de inicio de sesión",JOptionPane.ERROR_MESSAGE);
                    //limpiar campos
                    inputUsuario.setText(null);
                    inputPassword.setText(null);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //metodo para verificar credenciales
    private boolean veficarCredenciales (String usuario, String password) {
        //acceso al array list de la clase RegistroUsuarios
        ArrayList<Usuario> usuarios = registroUsuario.getUsuarios();
        //bucle que recorre el registro y verifica
        for (Usuario user: usuarios){
            //compara usuario y contraseña ingresados con los datos ya almacenados
            if(user.getNombre().equals(usuario) && user.getPassword().equals(password)){
                //usuario y contraseña correctos
                return true;
            }
        }
        //usuario o contraseña incorrectos
        return false;
    }
}



