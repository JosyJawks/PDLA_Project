package View;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import Controller.MissionController;
import Model.Volunteer;
import Model.Mission;

public class VolunteerApp extends JPanel implements ListSelectionListener{

    private List<Mission> missionInfoList;

    private final List<String> nameList;
    private final JList<String> missionList;
    private final DefaultListModel<String> missionListModel;

    private final JButton acceptButton;
    private final JButton cancelButton;

    public VolunteerApp(Volunteer v) {
        JFrame frame = new JFrame("Mission Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a label to display the client's name and surname
        JLabel nameLabel = new JLabel("Volunteer : " + v.getName() + " " + v.getSurname());

        // Load missions from the database for the client
        missionInfoList = MissionController.getMissionsForVolunteer();
        nameList = MissionController.getNamesForVolunteer();

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

        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false); // Initially disabled
        cancelButton.addActionListener(new cancelMission());

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

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
            String selectedClient = nameList.get(index);

            if (size == 0) {
                acceptButton.setEnabled(false);

            } else { //Select an index.
                missionList.setSelectedIndex(index);
                missionList.ensureIndexIsVisible(index);

                System.out.println("index = " + index);
                System.out.println("objective = " + selectedMission.getObjective());
                MissionController.changeMissionStatus(selectedMission, selectedClient,"Confirmed");

                updateMission();
            }
        }
    }

    class cancelMission implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = missionList.getSelectedIndex();
            int size = missionListModel.getSize();
            Mission selectedMission = missionInfoList.get(index);
            String selectedClient = nameList.get(index);

            if (size == 0) {
                cancelButton.setEnabled(false);

            } else { //Select an index.
                missionList.setSelectedIndex(index);
                missionList.ensureIndexIsVisible(index);

                System.out.println("index = " + index);
                System.out.println("objective = " + selectedMission.getObjective());
                MissionController.changeMissionStatus(selectedMission, selectedClient,"Pending");

                updateMission();
            }
        }
    }


    private void getMissions() {
        List<String> completeList = new ArrayList<>();

        for (String names : nameList) {
            completeList.add("Client : " + names);
        }

        int index = 0;
        for (Mission mission : missionInfoList) {
            completeList.set(index, completeList.get(index) + " | "
                    + "Mission : " + mission.getObjective() + " / "
                    + "Location : " + mission.getLocation() + " / "
                    + "Mission Date : " + mission.getDateMission() + " / "
                    + "Creation Date : " + mission.getDateCreation() + " / "
                    + "Status : " + mission.getStatus());
            index++;
        }

        for(String task : completeList) {
            missionListModel.addElement(task);
        }

    }


    private void updateMission() {
        missionListModel.clear();
        missionInfoList = MissionController.getMissionsForVolunteer();
        getMissions();
    }


    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {
            if(missionList.getSelectedIndex() == -1) {
                acceptButton.setEnabled(false);
                cancelButton.setEnabled(false);
            } else {
                int index = missionList.getSelectedIndex();
                Mission selectedMission = missionInfoList.get(index);
                if(selectedMission.getStatus().equals("Pending")) {
                    acceptButton.setEnabled(true);
                    cancelButton.setEnabled(false);
                }
                else{
                    acceptButton.setEnabled(false);
                    cancelButton.setEnabled(true);
                }
            }
        }
    }

}
