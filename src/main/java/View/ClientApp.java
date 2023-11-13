package View;

import Controller.MissionController;
import Model.Client;

import javax.swing.*;
import java.awt.*;


public class ClientApp extends JFrame{

    private Client connectedClient;

    public ClientApp(Client c) {

        this.connectedClient = c;

        JFrame frame = new JFrame("Client Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Name :" + connectedClient.getName() + connectedClient.getSurname());

        //JLabel typeLabel = new JLabel("Email:");


        // Create buttons for accepting and declining missions
        JButton CreateMissionButton = new JButton("Create Mission");
        CreateMissionButton.addActionListener(e -> {
            SwingUtilities.invokeLater(MissionApp::new);
        });
        panel.add(nameLabel);
        panel.add(CreateMissionButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}

