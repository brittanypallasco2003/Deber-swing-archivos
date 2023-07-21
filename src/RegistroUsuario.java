import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class RegistroUsuario {
    private JPanel rootPanel;
    private JTextField inputUser;
    private JPasswordField inputPassword;
    private JButton crearUserButton;
    private JButton iniciarSesiónButton;
    private JPasswordField inputComprobar;

    //Array para guardar info de usuarios
    ArrayList<Usuario>usuarios=new ArrayList<Usuario>();

    //para acceder a array desde otra clase
    public ArrayList<Usuario> getUsuarios(){
        return usuarios;
    }

    //Ruta del .dat
    String filepath="usuario.dat";
    public RegistroUsuario() {
        crearUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPassword.setEchoChar('*');
                inputComprobar.setEchoChar('*');
                //Se define un objeto de la clase Usuario y sus atributos
                String nombre=inputUser.getText();
                String password=String.valueOf(inputPassword.getPassword());
                String passwordComprobado=String.valueOf(inputComprobar.getPassword());

                if(password.equals(passwordComprobado)){
                   Usuario user=new Usuario(nombre,password);
                   usuarios.add(user);

                   try (FileOutputStream fileOutputStream=new FileOutputStream(filepath);
                        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
                       )
                   {
                       for(Usuario usuario:usuarios){
                          objectOutputStream.writeObject(usuario.getNombre());
                          objectOutputStream.writeObject(usuario.getPassword());
                       }
                   }catch (IOException exception){
                       throw new RuntimeException(new Exception(exception));
                   }
                   inputUser.setText("");
                   inputPassword.setText("");
                   inputComprobar.setText("");
                }else {
                    JOptionPane.showMessageDialog(null,"La contraseñas ingresadas no son las mismas");
                    inputPassword.setEchoChar((char)0);
                    inputComprobar.setEchoChar((char)0);
                }
            }
        });
        iniciarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrar_ventana();
            }
        });
    }

    public void mostrar_ventana(){
        Login login=new Login(this);
        JFrame frameLogin = new JFrame("Login");
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setSize(500,500);
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setVisible(true);
        frameLogin.setContentPane(login.getRootPanel());
    }
    public static void main(String[] args) {
    JFrame frame= new JFrame("Estudiantes");
    frame.setContentPane(new RegistroUsuario().rootPanel);
    frame.setLocationRelativeTo(null);
    frame.setSize(800,800);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }

}


