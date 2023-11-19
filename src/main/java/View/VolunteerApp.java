package View;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import Controller.MissionController;
import Model.Volunteer;
import Model.Mission;

public class VolunteerApp extends JPanel implements ListSelectionListener{

    private final List<Mission> missionInfoList;
    private final JList<String> missionList;
    private final DefaultListModel<String> missionListModel;

    private final JButton acceptButton;
    private final JButton declineButton;

    public VolunteerApp(Volunteer v) {
        JFrame frame = new JFrame("Mission Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a label to display the client's name and surname
        JLabel nameLabel = new JLabel("Volunteer : " + v.getName() + " " + v.getSurname());

        // Load missions from the database for the client
        missionInfoList = MissionController.getMissionsForVolunteer();

        // Create a list for displaying missions
        missionListModel = new DefaultListModel<>();
        getMissions();

        missionList = new JList<>(missionListModel);
        missionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        missionList.setSelectedIndex(0);
        missionList.addListSelectionListener(this);
        missionList.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(missionList);

        // Create buttons for accepting and declining missions
        acceptButton = new JButton("Accept");
        acceptButton.setActionCommand("Accept"); // Initially disabled
        acceptButton.addActionListener(new acceptMission());

        declineButton = new JButton("Decline");
        declineButton.setEnabled(false); // Initially disabled
        declineButton.addActionListener(new declineMission());

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(acceptButton);
        buttonPanel.add(declineButton);

        // Add components to the main panel
        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

    }

    class acceptMission implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = missionList.getSelectedIndex();
            int size = missionListModel.getSize();
            Mission selectedMission = missionInfoList.get(index);

            if (size == 0) {
                acceptButton.setEnabled(false);

            } else { //Select an index.
                missionList.setSelectedIndex(index);
                missionList.ensureIndexIsVisible(index);

                System.out.println("index = " + index);
                System.out.println("objective = " + selectedMission.getObjective());
                MissionController.changeMissionStatus(selectedMission);

                updateMission(selectedMission,index);
            }
        }
    }

    class declineMission implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = missionList.getSelectedIndex();
            int size = missionListModel.getSize();
            missionListModel.remove(index);

            if (size == 0) {
                declineButton.setEnabled(false);

            } else { //Select an index.
                if(index == missionListModel.getSize()){
                    index--;
                }
                missionList.setSelectedIndex(index);
                missionList.ensureIndexIsVisible(index);
            }
        }
    }


    private void getMissions() {
        for (Mission mission : missionInfoList) {
            missionListModel.addElement("Mission : " + mission.getObjective() + " | "
                    + "Location : " + mission.getLocation() + " | "
                    + "Mission Date : " + mission.getDateMission() + " | "
                    + "Creation Date : " + mission.getDateCreation() + " | "
                    + "Status : " + mission.getStatus());
        }

    }


    private void updateMission(Mission mission,int index) {

        missionListModel.setElementAt("Mission : " + mission.getObjective() + " | "
                + "Location : " + mission.getLocation() + " | "
                + "Mission Date : " + mission.getDateMission() + " | "
                + "Creation Date : " + mission.getDateCreation() + " | "
                + "Status : Confirmed",index);

    }


    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {
            if(missionList.getSelectedIndex() == -1) {
                acceptButton.setEnabled(false);
                declineButton.setEnabled(false);
            } else {
                int index = missionList.getSelectedIndex();
                Mission selectedMission = missionInfoList.get(index);
                if(selectedMission.getStatus().equals("Pending")) {
                    acceptButton.setEnabled(true);
                    declineButton.setEnabled(true);
                }
                else{
                    acceptButton.setEnabled(false);
                    declineButton.setEnabled(false);
                }
            }
        }
    }

}
