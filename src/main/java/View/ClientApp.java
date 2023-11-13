package View;

import Controller.MissionController;

import javax.swing.*;
import java.awt.*;


public class ClientApp {


    public ClientApp() {
        JFrame frame = new JFrame("Client Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a list for displaying missions
        //missionListModel = new DefaultListModel<>();
        //missionList = new JList<>(missionListModel);
        //JScrollPane listScrollPane = new JScrollPane(missionList);

        // Create buttons for accepting and declining missions
        JButton CreateMissionButton = new JButton("Create Mission");
        CreateMissionButton.addActionListener(e -> {
            //call controller
        });

        panel.add(CreateMissionButton);

        frame.add(panel);
        frame.setVisible(true);
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientApp::new);
    }
}

