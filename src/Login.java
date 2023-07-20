import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel rootPanel;
    private JTextField textField1;
    private JButton iniciarSesionButton;
    private JPasswordField inputPassword;
    private JCheckBox seePasswordCheckBox;

    public JPanel getRootPanel() {
        return rootPanel;
    }
    //Listener
    public Login() {
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
    }

}



