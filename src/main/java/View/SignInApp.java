package View;

import Controller.UserController;

import javax.swing.*;
import java.awt.*;

public class SignInApp extends JFrame {

    private static String[] Final;
    private final JTextField emailTextField;
    private final JPasswordField passwordField;

    public SignInApp() {
        JFrame frame = new JFrame("Sign In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(e -> {
            Final = creerSignIn();
            UserController.SignIn();
            frame.setVisible(false);
        });

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

        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(signUpbuttonPanel);
        panel.add(signInbuttonPanel);

        frame.add(panel);
        frame.setVisible(true);
    }

    private String[] creerSignIn() {
        String email = emailTextField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        return new String[]{email, password};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignInApp::new);
    }

    public static String[] getFinal() {
        return Final;
    }
    public static void setFinal(String[] finalData) {
        Final = finalData;
    }
}
