package View;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class SignUpApp extends JPanel{

    private static String[] Final;
    private final JTextField nameTextField;
    private final JTextField surnameTextField;
    private final JTextField emailTextField;
    private String typeTextField;
    private final JPasswordField passwordField;
    private final JPasswordField confirmPasswordField;

    public SignUpApp(){
        // Create a new JFrame for the SignUpApp
        JFrame frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create a panel for organizing components with GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        // Labels and text fields for sign up details
        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();

        JLabel surnameLabel = new JLabel("Surname:");
        surnameTextField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();

        JLabel typeLabel = new JLabel("Type:");

        //create radios button to select if the user is a client or a volunteer
        JPanel panelType = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton clientRadioButton = new JRadioButton("Client", true);
        JRadioButton volunteerRadioButton = new JRadioButton("Volunteer", false);

        //button group with the 2 radios button to be able to select only one at a time
        ButtonGroup groupType = new ButtonGroup();
        groupType.add(clientRadioButton);
        groupType.add(volunteerRadioButton);
        panelType.add(clientRadioButton);
        panelType.add(volunteerRadioButton);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField();

        //creation of the sign-up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> {
            boolean access;
            typeTextField = getType(groupType);
            Final = creerSignUp();
            access = UserController.SignUp();
            if(access) {
                frame.setVisible(false);
                SignInApp signInApp = new SignInApp();
                signInApp.setVisible(true);
            }
        });

        // Create a panel for the button and center it
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(signUpButton);

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(surnameLabel);
        panel.add(surnameTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(typeLabel);
        panel.add(panelType);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);

        panel.add(new JLabel()); // Empty label for spacing
        panel.add(buttonPanel);

        // Add the panel to the JFrame
        frame.add(panel);
        frame.setVisible(true);
    }

    //set the final information from the interface
    public static void setFinal(String[] finalData) {
        Final = finalData;
    }
    //get the information of the 2 radios button to return the type : Client or Volunteer
    private String getType(ButtonGroup groupType) {
        for (Enumeration<AbstractButton> buttons = groupType.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    //return all the information from the interface
    private String[] creerSignUp() {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String email = emailTextField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        char[] confirmPasswordChars = confirmPasswordField.getPassword();
        String confirmPassword = new String(confirmPasswordChars);
        String type = typeTextField;
        return new String[]{name, surname, email, type, password, confirmPassword};
    }


    //return the final information
    public static String[] getFinal() {
        return Final;
    }
}
