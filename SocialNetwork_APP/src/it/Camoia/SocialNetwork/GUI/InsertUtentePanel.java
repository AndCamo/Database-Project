package it.Camoia.SocialNetwork.GUI;

import javax.swing.*;
import java.awt.*;

public class InsertUtentePanel extends JPanel {

    public InsertUtentePanel(){
        this.setLayout(new GridLayout(7, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cognomeLabel = new JLabel("Cognome:");
        JLabel eMailLabel = new JLabel("eMail:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel dateLabel = new JLabel("Data di Nascita:");
        JLabel nazioneLabel = new JLabel("Nazionalità:");
        usernameField = new JTextField(20);
        nomeField = new JTextField(20);
        cognomeField = new JTextField(20);
        eMailField = new JTextField(20);
        passwordField = new JTextField(20);
        dateField = new JTextField(20);
        nazioneField = new JTextField(20);
        nazioneField.setToolTipText("Ciao");

        this.add(usernameLabel);
        this.add(usernameField);
        this.add(nomeLabel);
        this.add(nomeField);
        this.add(cognomeLabel);
        this.add(cognomeField);
        this.add(eMailLabel);
        this.add(eMailField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(dateLabel);
        this.add(dateField);
        this.add(nazioneLabel);
        this.add(nazioneField);
    }



    protected JTextField usernameField;
    protected JTextField nomeField;
    protected JTextField cognomeField;
    protected JTextField nazioneField;
    protected JTextField eMailField;
    protected JTextField passwordField;
    protected JTextField dateField;
}
