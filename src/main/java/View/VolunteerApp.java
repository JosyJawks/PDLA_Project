package View;

import javax.swing.*;
import java.awt.*;


public class VolunteerApp extends JFrame{

    private final JList<String> missionList;
    private final DefaultListModel<String> missionListModel;

    public VolunteerApp() {
        JFrame frame = new JFrame("Mission Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a list for displaying missions
        missionListModel = new DefaultListModel<>();
        missionList = new JList<>(missionListModel);
        JScrollPane listScrollPane = new JScrollPane(missionList);

        // Create buttons for accepting and declining missions
        JButton acceptButton = new JButton("Accept");
        acceptButton.setEnabled(false); // Initially disabled
        acceptButton.addActionListener(e -> acceptMission());

        JButton declineButton = new JButton("Decline");
        declineButton.setEnabled(false); // Initially disabled
        declineButton.addActionListener(e -> declineMission());

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(acceptButton);
        buttonPanel.add(declineButton);

        // Add components to the main panel
        panel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void acceptMission() {
        // Implement logic to accept the selected mission
        int selectedIndex = missionList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedMission = missionListModel.get(selectedIndex);
            // Add your logic here to handle mission acceptance
            System.out.println("Accepted mission: " + selectedMission);
        }
    }

    private void declineMission() {
        // Implement logic to decline the selected mission
        int selectedIndex = missionList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedMission = missionListModel.get(selectedIndex);
            // Add your logic here to handle mission decline
            System.out.println("Declined mission: " + selectedMission);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VolunteerApp::new);
    }
}
