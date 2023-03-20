package it.Camoia.SocialNetwork.GUI;

import it.Camoia.SocialNetwork.Entity.Utente;
import it.Camoia.SocialNetwork.SocialNetworkManager;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SocialNetworkFrame extends JFrame {

    public SocialNetworkFrame(){
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        this.add(createCenterPanel(), BorderLayout.SOUTH);
        this.add(createTopPanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel(){
        topPanel = new JPanel();
        printButton = new JButton("Stampa Utenti");
        printButton.addActionListener(listener -> {
            try {
                List<Utente> listaUtenti = SocialNetworkGUI.socialManager.getUtenti();
                textArea.setText("--- STAMPO GLI UTENTI ---\n\n");
                for (Utente tmpUtente : listaUtenti){
                    textArea.append(tmpUtente.toString() + "\n");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Errore durante la stampa", "Errore!", JOptionPane.ERROR_MESSAGE);
            }
        });
        insertButton = new JButton("Inserisci Utente");
        insertButton.addActionListener(listener -> {
            InsertUtentePanel insertUtentePanel = new InsertUtentePanel();
            int result = JOptionPane.showConfirmDialog(SocialNetworkFrame.this, insertUtentePanel, "Inserisci Nuovo Utente", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION){
                try {
                String username = insertUtentePanel.usernameField.getText();
                String nome = insertUtentePanel.nomeField.getText();
                String cognome = insertUtentePanel.cognomeField.getText();
                String eMail = insertUtentePanel.eMailField.getText();
                String password = insertUtentePanel.passwordField.getText();
                String nazione = insertUtentePanel.nazioneField.getText();
                String data = insertUtentePanel.dateField.getText();
                /*if (data.matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}") == false) {
                    System.out.println("Ho letto: " + data);
                    throw new IllegalArgumentException();
                }*/
                System.out.println(data);
                Utente tmpUtente = new Utente(username, nome, cognome, eMail, password, nazione, data);
                System.out.println(tmpUtente.getDataDiNascita());
                SocialNetworkGUI.socialManager.insertUtente(tmpUtente);
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        topPanel.add(printButton);
        topPanel.add(insertButton);

        return topPanel;
    }

    private JPanel createCenterPanel(){
        centerPanel = new JPanel(new GridLayout(1,1));
        textArea = new TextArea(30, 100);
        textArea.setEditable(false);
        centerPanel.add(textArea);
        centerPanel.setBorder(new TitledBorder(new EtchedBorder(), "Output"));
        return centerPanel;
    }




    protected JPanel topPanel;
    protected JButton printButton;
    protected JButton insertButton;
    protected JPanel centerPanel;
    protected TextArea textArea;
    public static final int FRAME_WIDTH = 1080;
    public static final int FRAME_HEIGHT = 720;
}
