package View;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.Objects;


//name surname email Client/Volunteer password confirm_password 

public class SignUpApp {

    private static String[] Final;
    private final JTextField nameTextField;
    private final JTextField surnameTextField;
    private final JTextField emailTextField;

    private String typeTextField;
    private final JTextField passwordTextField;

    public SignUpApp() {
        //test
        JFrame frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();

        JLabel surnameLabel = new JLabel("Surname:");
        surnameTextField = new JTextField();
        
        JLabel emailLabel = new JLabel("e-mail:");
        emailTextField = new JTextField();

        JLabel typeLabel = new JLabel("Type:");

        JPanel paneltype = new JPanel();
        paneltype.setLayout(new GridLayout(1, 2));

        JRadioButton Client = new JRadioButton("Client", true);
        JRadioButton Volunteer = new JRadioButton("Volunteer", false);

        ButtonGroup grouptype = new ButtonGroup();
        grouptype.add(Client);
        grouptype.add(Volunteer);

        paneltype.add(Client);
        paneltype.add(Volunteer);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordTextField = new JTextField();
        
        JLabel cpasswordLabel = new JLabel("Confirm password:");
        JTextField cpasswordTextField = new JTextField();
        
       
        JButton creerSignUpButton = new JButton("Sign Up");
        creerSignUpButton.addActionListener(e -> {
            if (Objects.equals(passwordTextField.getText(), cpasswordLabel.getText())) {
                typeTextField = getType(grouptype);
                Final=creerSignUp();

            }
        });



        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(surnameLabel);
        panel.add(surnameTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(typeLabel);
        panel.add(paneltype);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(cpasswordLabel);
        panel.add(cpasswordTextField);
        
        panel.add(new JLabel());
        panel.add(creerSignUpButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public String getType(ButtonGroup grouptype)
    {
        for (Enumeration<AbstractButton> buttons = grouptype.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }


    private String [] creerSignUp() {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String type = typeTextField;
        return new String[]{name, surname, email, type, password};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignUpApp::new);
    }

    public static String[] getFinal() {
        return Final;
    }
}
