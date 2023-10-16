package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MissionApp {

    private JFrame frame;
    private JTextField objectifTextField;
    private JTextField lieuTextField;
    private JTextField dateMissionTextField;
    
    public MissionApp() {
        frame = new JFrame("Gestion des Missions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel objectifLabel = new JLabel("Objectif:");
        objectifTextField = new JTextField();

        JLabel lieuLabel = new JLabel("Lieu:");
        lieuTextField = new JTextField();
        
        JLabel dateMissionLabel = new JLabel("Date de la mission:");
        dateMissionTextField = new JTextField();
        
        JLabel dateCreationLabel = new JLabel("Date de création de la mission:");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date currentDate = new Date();
        dateCreationLabel.setText(dateFormat.format(currentDate));


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
        panel.add(dateMissionLabel);
        panel.add(dateMissionTextField);
        panel.add(dateCreationLabel);
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
