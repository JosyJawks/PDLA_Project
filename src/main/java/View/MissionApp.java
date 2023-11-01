package View;

import Controller.MissionController;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MissionApp {

    private static String[] Final;
    private final JTextField objectifTextField;
    private final JTextField lieuTextField;
    private final JTextField dateMissionTextField;

    public MissionApp() {
        JFrame frame = new JFrame("New Mission");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel objectifLabel = new JLabel("Objective:");
        objectifTextField = new JTextField();

        JLabel lieuLabel = new JLabel("Location:");
        lieuTextField = new JTextField();

        JLabel dateMissionLabel = new JLabel("Date of the mission:");
        dateMissionTextField = new JTextField();

        JLabel dateCreationLabel = new JLabel("Date of creation:");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        dateCreationLabel.setText(currentDateString);

        JButton creerMissionButton = new JButton("Create Mission");
        creerMissionButton.addActionListener(e -> {
            Final = creerMission();
            MissionController.Mission();
        });

        panel.add(objectifLabel);
        panel.add(objectifTextField);
        panel.add(lieuLabel);
        panel.add(lieuTextField);
        panel.add(dateMissionLabel);
        panel.add(dateMissionTextField);
        panel.add(dateCreationLabel);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(creerMissionButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private String[] creerMission() {
        String location = lieuTextField.getText();
        String datemission = dateMissionTextField.getText();
        String objective = objectifTextField.getText();
        String datecreation = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        return new String[]{location, datemission, objective, datecreation};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MissionApp::new);
    }

    public static String[] getFinal() {
        return Final;
    }
}
