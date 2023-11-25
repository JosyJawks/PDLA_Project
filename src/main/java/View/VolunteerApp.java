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

    // Mission list obtained from database
    private List<Mission> missionInfoList;

    // Mission list used for interface
    private final JList<String> missionList;

    // Mission list model displayed on interface
    private final DefaultListModel<String> missionListModel;

    private final JButton acceptButton;
    private final JButton cancelButton;

    public VolunteerApp(Volunteer v) {
        // Create a new JFrame for the MissionApp
        JFrame frame = new JFrame("Mission Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 400);
        frame.setLocationRelativeTo(null);

        // Create a panel for organizing components with GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a label to display the volunteer's name and surname
        JLabel nameLabel = new JLabel("Volunteer : " + v.getName() + " " + v.getSurname());

        // Load missions from the database for the client
        missionInfoList = MissionController.getMissionsForVolunteer(v);

        // Create a list for displaying missions
        missionListModel = new DefaultListModel<>();
        addMissionsToDisplayedList();

        // Setup mission list so that we can select objects on the interface
        missionList = new JList<>(missionListModel);
        missionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one object selectable at a time
        missionList.setSelectedIndex(0);
        missionList.addListSelectionListener(this);
        missionList.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(missionList);

        // Create buttons for accepting and cancelling missions
        acceptButton = new JButton("Accept");
        acceptButton.setActionCommand("Accept"); // Initially disabled
        acceptButton.addActionListener(new acceptMission(v));

        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false); // Initially disabled
        cancelButton.addActionListener(new cancelMission(v));

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        // Add components to the main panel
        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the JFrame
        frame.add(panel);
        frame.setVisible(true);

    }

    //Class called when acceptButton is used
    class acceptMission implements ActionListener {
        //Action performed when acceptButton is used
        Volunteer v;
        public acceptMission(Volunteer v)
        {
            this.v=v;
        }
        public void actionPerformed(ActionEvent e) {
            // Index of the selected mission on the interface
            int index = missionList.getSelectedIndex();
            // Size of the list of missions displayed on the interface
            int size = missionListModel.getSize();
            // Get the actual mission object from the missionInfoList previously created
            Mission selectedMission = missionInfoList.get(index);

            // If the list displayed on the interface is empty, acceptButton is disabled
            if (size == 0) {
                acceptButton.setEnabled(false);

            } else { //Select an index.
                // Set the selectedIndex to the index from getSelectedIndex() method
                missionList.setSelectedIndex(index);
                // Highlights the selected mission
                missionList.ensureIndexIsVisible(index);

                // Update the mission status in SQL database from Pending to Confirmed
                MissionController.changeMissionStatusConfirmed(selectedMission,v);

                // Update the displayed list on the interface
                updateMission(v);
            }
        }
    }

    //Class called when cancelButton is used
    class cancelMission implements ActionListener {

        Volunteer v;
        public cancelMission(Volunteer v)
        {
            this.v=v;
        }
        //Action performed when cancelButton is used
        public void actionPerformed(ActionEvent e) {
            // Index of the selected mission on the interface
            int index = missionList.getSelectedIndex();
            // Size of the list of missions displayed on the interface
            int size = missionListModel.getSize();
            // Get the actual mission object from the missionInfoList previously created
            Mission selectedMission = missionInfoList.get(index);

            // If the list displayed on the interface is empty, cancelButton is disabled
            if (size == 0) {
                cancelButton.setEnabled(false);

            } else { //Select an index.
                // Set the selectedIndex to the index from getSelectedIndex() method
                missionList.setSelectedIndex(index);
                // Highlights the selected mission
                missionList.ensureIndexIsVisible(index);

                // Update the mission status in SQL database from Confirmed to Pending
                MissionController.changeMissionStatusPending(selectedMission);

                // Update the displayed list on the interface
                updateMission(v);
            }
        }
    }

    // Add missions and their author to the missionListModel displayed on the interface
    private void addMissionsToDisplayedList() {

        //List of missions
        for (Mission mission : missionInfoList) {
            missionListModel.addElement("Client : " + mission.getClient().getName() + " " + mission.getClient().getSurname()
                    + " - Mission : " + mission.getObjective() + " / "
                    + "Location : " + mission.getLocation() + " / "
                    + "Mission Date : " + mission.getDateMission() + " / "
                    + "Creation Date : " + mission.getDateCreation() + " / "
                    + "Status : " + mission.getStatus());
        }

    }


    // Update the displayed list on the interface
    private void updateMission(Volunteer vol) {
        //Delete everything from displayed list
        missionListModel.clear();
        // Get mission List from the SQL database
        missionInfoList = MissionController.getMissionsForVolunteer(vol);
        addMissionsToDisplayedList();
    }


    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {
            // If no mission is selected in the interface
            if(missionList.getSelectedIndex() == -1) {
                // Buttons are disabled
                acceptButton.setEnabled(false);
                cancelButton.setEnabled(false);
            } else {
                int index = missionList.getSelectedIndex();
                Mission selectedMission = missionInfoList.get(index);
                // If mission status is Pending
                if(selectedMission.getStatus().equals("Pending")) {
                    //acceptButton is enabled, cancelButton is disabled
                    acceptButton.setEnabled(true);
                    cancelButton.setEnabled(false);
                }
                // If mission status is Confirmed
                else{
                    //acceptButton is disabled, cancelButton is enabled
                    acceptButton.setEnabled(false);
                    cancelButton.setEnabled(true);
                }
            }
        }
    }

}
