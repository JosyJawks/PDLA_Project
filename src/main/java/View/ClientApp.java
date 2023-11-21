package View;

import Controller.MissionController;
import Model.Client;
import Model.Mission;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientApp extends JPanel {

    private final List<Mission> missionList;
    private final JPanel missionsPanel;
    private final JFrame frame;

    public ClientApp(Client c) {
        // Initialize the main JFrame
        frame = new JFrame("Client Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        // Create a main panel with BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a label to display the client's name and surname
        JLabel nameLabel = new JLabel("Client : " + c.getName() + " " + c.getSurname());

        // Create a button for creating missions
        JButton createMissionButton = new JButton("Create Mission");
        createMissionButton.addActionListener(e -> {
            // Open a new MissionApp window when the button is clicked
            MissionApp missionApp = new MissionApp(c, this);
            missionApp.setVisible(true);
        });

        // Create a panel to display missions with a vertical layout
        missionsPanel = new JPanel();
        missionsPanel.setLayout(new BoxLayout(missionsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(missionsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add components to the main panel using BorderLayout
        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(createMissionButton, BorderLayout.SOUTH);

        // Add the main panel to the main frame
        frame.add(panel);
        frame.setVisible(true);

        // Load missions from the database for the client
        missionList = MissionController.getMissionsForClient(c);

        // Display the loaded missions in the panel
        displayMissions();
    }

    // Method to update the list of missions and refresh the interface
    public void updateMissionList(Mission newMission) {
        missionList.add(newMission);

        // Create a panel to display details of the new mission
        JPanel missionInfoPanel = new JPanel();
        missionInfoPanel.setLayout(new GridLayout(5, 1));

        missionInfoPanel.add(new JLabel("Mission: " + newMission.getObjective()));
        missionInfoPanel.add(new JLabel("Location: " + newMission.getLocation()));
        missionInfoPanel.add(new JLabel("Mission Date: " + newMission.getDateMission()));
        missionInfoPanel.add(new JLabel("Creation Date: " + newMission.getDateCreation()));
        missionInfoPanel.add(new JLabel("Status: " + newMission.getStatus()));

        // Add the mission details panel to the main missions panel
        missionsPanel.add(missionInfoPanel);
        missionsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between missions

        // Refresh the main frame
        frame.revalidate();
        frame.repaint();
    }

    // Method to display the list of missions in the panel
    private void displayMissions() {
        // Clean the panel before adding new missions
        missionsPanel.removeAll();

        // Iterate through the list of missions and create panels for each
        for (Mission mission : missionList) {
            //creation of a panel for displaying the mission
            JPanel missionInfoPanel = new JPanel();
            missionInfoPanel.setLayout(new GridLayout(5, 1));

            missionInfoPanel.add(new JLabel("Mission: " + mission.getObjective()));
            missionInfoPanel.add(new JLabel("Location: " + mission.getLocation()));
            missionInfoPanel.add(new JLabel("Mission Date: " + mission.getDateMission()));
            missionInfoPanel.add(new JLabel("Creation Date: " + mission.getDateCreation()));
            missionInfoPanel.add(new JLabel("Status: " + mission.getStatus()));

            // Add the mission information panel to the main missions panel
            missionsPanel.add(missionInfoPanel);
            missionsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between missions
        }
        // Refresh the main frame
        frame.revalidate();
        frame.repaint();
    }
}
