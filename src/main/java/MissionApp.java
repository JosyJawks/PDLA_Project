//créer 2 interfaces pour application java
// Date: 24/10/2019

//une interface Client et une interface Volunteer
//Le client peut créer des missions (via un Form) et le Volunteer peut les voir et en accepter
//Le client peut voir les missions qu'il a créé et les modifier
//Le Volunteer peut voir les missions qu'il a accepté et les modifier



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MissionApp {

    private JFrame frame;
    private JTextField objectifTextField;
    private JTextField lieuTextField;

    public MissionApp() {
        frame = new JFrame("Gestion des Missions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel objectifLabel = new JLabel("Objectif:");
        objectifTextField = new JTextField();

        JLabel lieuLabel = new JLabel("Lieu:");
        lieuTextField = new JTextField();

        JButton creerMissionButton = new JButton("Créer Mission");
        creerMissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creerMission();
            }
        });

        panel.add(objectifLabel);
        panel.add(objectifTextField);
        panel.add(lieuLabel);
        panel.add(lieuTextField);
        panel.add(new JLabel());
        panel.add(creerMissionButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void creerMission() {
        //String objectif = objectifTextField.getText();
        //String lieu = lieuTextField.getText();

        // Ajoutez ici la logique pour créer une mission avec les données fournies
        // (par exemple, enregistrer dans la base de données).
        // Vous devrez également mettre à jour l'interface pour afficher les missions disponibles aux bénévoles.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MissionApp();
            }
        });
    }
}
