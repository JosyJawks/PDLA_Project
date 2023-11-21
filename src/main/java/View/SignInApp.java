package View;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;

public class SignInApp extends JFrame {

    private static String[] Final;
    private final JTextField emailTextField;
    private final JPasswordField passwordField;

    public SignInApp() {
        // Create a new JFrame for the SignInApp
        JFrame frame = new JFrame("Sign In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create a panel for organizing components with GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Labels and text fields for sign in details
        JLabel emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        //Creation of the SignIn button
        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(e -> {
            Final = creerSignIn();
            UserController.SignIn();
            frame.setVisible(false);
        });

        //Creation of the SignUp button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> {
            frame.setVisible(false);
            SignUpApp signUpApp = new SignUpApp();
            signUpApp.setVisible(true);
        });

        // Create a panel for the button and center it
        JPanel signInbuttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel signUpbuttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        signInbuttonPanel.add(signInButton);
        signUpbuttonPanel.add(signUpButton);

        // Add components to the panel
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(signUpbuttonPanel);
        panel.add(signInbuttonPanel);

        // Add the panel to the JFrame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to create a string with entered details
    private String[] creerSignIn() {
        String email = emailTextField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        return new String[]{email, password};
    }

    //return the final information
    public static String[] getFinal() {
        return Final;
    }
    //set the final information with information from the interface
    public static void setFinal(String[] finalData) {
        Final = finalData;
    }
}
