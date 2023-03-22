package it.Camoia.SocialNetwork.GUI;

import it.Camoia.SocialNetwork.Entity.Utente;
import it.Camoia.SocialNetwork.Exception.BadUtenteException;
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
        printButton.setPreferredSize(new Dimension(150, 80));
        printButton.setFont(new Font("monospace", Font.BOLD, 15));
        printButton.addActionListener(listener -> {
            try {
                List<Utente> listaUtenti = SocialNetworkGUI.socialManager.getUtenti();
                textArea.setText("");
                textArea.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                textArea.append(String.format("%-20s   \t%-12s   \t%-12s   \t%-20s   \t%-20s\t\t   %-25s   \t%-15s", "USERNAME", "NOME", "COGNOME", "PASSWORD", "E-MAIL", "NAZIONALITÀ", "DATA DI NASCITA"));
                textArea.append("\n");
                textArea.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                //iterates over the list
                for(Utente tmpUtente: listaUtenti)
                {
                    textArea.append(String.format("%-20s   \t%-12s   \t%-12s   \t%-20s   \t%-20s\t\t   %-25s   \t%-15s", tmpUtente.getUsername(), tmpUtente.getNome(), tmpUtente.getCognome(), tmpUtente.getPassword(), tmpUtente.geteMail(), tmpUtente.getNazione(), tmpUtente.getDataDiNascita()));
                    textArea.append("\n");
                }
                textArea.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                /*textArea.setText("--- STAMPO GLI UTENTI ---\n\n");
                for (Utente tmpUtente : listaUtenti){
                    textArea.append(tmpUtente.toString() + "\n");
                }*/
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Errore durante la stampa", "Errore!", JOptionPane.ERROR_MESSAGE);
            }
        });
        insertButton = new JButton("Inserisci Utente");
        insertButton.setPreferredSize(new Dimension(150, 80));
        insertButton.setFont(new Font("monospace", Font.BOLD, 15));
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
                if (!data.matches("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}")){
                    System.out.println("Ho letto: " + data);
                    throw new IllegalArgumentException();
                }
                System.out.println(data);
                Utente tmpUtente = new Utente(username, nome, cognome, eMail, password, nazione, data);



                System.out.println(tmpUtente.getDataDiNascita());
                SocialNetworkGUI.socialManager.insertUtente(tmpUtente);
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore!", JOptionPane.ERROR_MESSAGE);
                } catch (BadUtenteException ex) {
                    JOptionPane.showMessageDialog(this, "Errore parametri Utente!", "INSERIMENTO NON EFFETTUATO", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "Formato data sbagliato, usare il seguente: \n YYYY-MM-DD", "INSERIMENTO NON EFFETTUATO", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        deleteButton = new JButton("Elimina Utente");
        deleteButton.setPreferredSize(new Dimension(150, 80));
        deleteButton.setFont(new Font("monospace", Font.BOLD, 15));
        deleteButton.addActionListener(listener -> {
            DeleteUtentePanel deleteUtentePanel = null;
            try {
                deleteUtentePanel = new DeleteUtentePanel();
                int result = JOptionPane.showConfirmDialog(SocialNetworkFrame.this, deleteUtentePanel, "Quale utente vuoi eliminare?", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION){
                    String username = (String) deleteUtentePanel.utentiBox.getSelectedItem();
                    SocialNetworkGUI.socialManager.removeUtente(username);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Errore durante la Cancellazione", "Errore!", JOptionPane.ERROR_MESSAGE);
            }
        });

        topPanel.add(printButton);
        topPanel.add(insertButton);
        topPanel.add(deleteButton);
        return topPanel;
    }

    private JPanel createCenterPanel(){
        centerPanel = new JPanel(new GridLayout(1,1));
        textArea = new TextArea(30, 100);
        textArea.setFont(new Font("monospace", Font.BOLD, 13));
        textArea.setEditable(false);
        centerPanel.add(textArea);
        centerPanel.setBorder(new TitledBorder(new EtchedBorder(), "Output"));
        return centerPanel;
    }




    protected JPanel topPanel;
    protected JButton printButton;
    protected JButton insertButton;

    protected JButton deleteButton;
    protected JPanel centerPanel;
    protected TextArea textArea;
    public static final int FRAME_WIDTH = 1420;
    public static final int FRAME_HEIGHT = 720;
}
