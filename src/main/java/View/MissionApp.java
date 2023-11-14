package View;

import Controller.MissionController;
import Model.Client;
import Model.Mission;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MissionApp extends JFrame{

    private static Mission Final;
    private final JTextField objectifTextField;
    private final JTextField lieuTextField;
    private final JTextField dateMissionTextField;

    public MissionApp(Client c) {
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
            ClientApp cliApp = new ClientApp(c, Final);
            cliApp.setVisible(true);
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

    private Mission creerMission() {
        Mission mission = new Mission();
        mission.setStatus("Pending");
        mission.setDateCreation(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
        mission.setDateMission(dateMissionTextField.getText());
        mission.setLocation(lieuTextField.getText());
        mission.setObjective(objectifTextField.getText());
        return mission;
    }
}
