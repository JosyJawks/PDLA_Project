package View;

import Controller.MissionController;
import Model.Client;
import Model.Mission;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MissionApp extends JPanel {

    private final JTextField objectiveTextField;
    private final JTextField lieuTextField;
    private final JTextField dateMissionTextField;

    public MissionApp(Client c, ClientApp clientApp) {
        // Create a new JFrame for the MissionApp
        JFrame frame = new JFrame("New Mission");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        // Create a panel for organizing components with GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        // Labels and text fields for mission details
        JLabel objectiveLabel = new JLabel("Objective:");
        objectiveTextField = new JTextField();

        JLabel lieuLabel = new JLabel("Location:");
        lieuTextField = new JTextField();

        JLabel dateMissionLabel = new JLabel("Date of the mission:");
        dateMissionTextField = new JTextField();

        JLabel dateCreationLabel = new JLabel("Date of creation:");

        // Set the current date in the dateCreationLabel using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentDate = new Date();
        String currentDateString = dateFormat.format(currentDate);
        dateCreationLabel.setText(currentDateString);

        // Button to create and save the new mission
        JButton createMissionButton = new JButton("Create Mission");
        createMissionButton.addActionListener(e -> {
            boolean validMission;
            // Create a new mission with the entered details
            Mission createdMission = createMission(c);
            // Save the mission to the database
            validMission = MissionController.saveMission(createdMission);
            if(validMission) {
                // Update the mission list in the ClientApp interface
                clientApp.updateMissionList(createdMission);
                // Close the window after mission creation
                frame.dispose();
            }
        });

        // Add components to the panel
        panel.add(objectiveLabel);
        panel.add(objectiveTextField);
        panel.add(lieuLabel);
        panel.add(lieuTextField);
        panel.add(dateMissionLabel);
        panel.add(dateMissionTextField);
        panel.add(dateCreationLabel);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(createMissionButton);

        // Add the panel to the JFrame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to create a new Mission object with entered details
    private Mission createMission(Client client) {
        Mission mission = new Mission();
        mission.setStatus("Pending");
        mission.setDateCreation(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        mission.setDateMission(dateMissionTextField.getText());
        mission.setLocation(lieuTextField.getText());
        mission.setObjective(objectiveTextField.getText());
        mission.setClient(client);
        return mission;
    }
}
