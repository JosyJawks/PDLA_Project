package View;

import Controller.MissionController;
import Model.Client;
import Model.Mission;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ClientApp extends JFrame{
    List<Mission> missionList = new ArrayList<Mission>();

    public ClientApp(Client c, Mission l) {

        JFrame frame = new JFrame("Client Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Client :" + c.getName() + " " + c.getSurname());


        // Create buttons for accepting and declining missions
        JButton CreateMissionButton = new JButton("Create Mission");
        CreateMissionButton.addActionListener(e -> {
            MissionApp missionApp = new MissionApp(c);
            missionApp.setVisible(true);
        });
        //AJOUTER ICI LES MISSIONS RENVOYEES DE MISSIONAPP
        if (l!=null)
        {
            missionList.add(l);
            l=null;
            //foreach mission in missionlist faire un panel de mission vertical
            JPanel missionpanel = new JPanel();
            missionpanel.setLayout(new GridLayout(missionList.size(), 1));

        }

        panel.add(nameLabel);
        panel.add(CreateMissionButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}

