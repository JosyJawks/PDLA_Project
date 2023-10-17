package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//name surname email Client/Volunteer password confirm_password 

public class SignUpAppp {

    private JFrame frame;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField emailTextField;
    private JTextField typeTextField;
    private JTextField passwordTextField;
    private JTextField cpasswordTextField;
    
    public SignUpAppp() {
        frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();

        JLabel surnameLabel = new JLabel("Surname:");
        surnameTextField = new JTextField();
        
        JLabel emailLabel = new JLabel("e-mail:");
        emailTextField = new JTextField();
        
        JLabel typeLabel = new JLabel("Type:");
        typeTextField = new JTextField();
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordTextField = new JTextField();
        
        JLabel cpasswordLabel = new JLabel("Confirm password:");
        cpasswordTextField = new JTextField();
        
       
        JButton creerSignUpButton = new JButton("Sign Up");
        creerSignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creerSignUp();
            }
        });

        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(surnameLabel);
        panel.add(surnameTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(typeLabel);
        panel.add(typeTextField);
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

    private void creerSignUp() {
        //String name = nameTextField.getText();



    }

    public static void main(String[] args) {
    	System.out.println("*****");
        /*SwingUtilities.invokeLater(new Runnable() {
            @Override
           public void run() {
                new SignUpApp();
            
        });*/
    }
}
