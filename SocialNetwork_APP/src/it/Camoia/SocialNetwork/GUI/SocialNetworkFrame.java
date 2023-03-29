package it.Camoia.SocialNetwork.GUI;

import it.Camoia.SocialNetwork.Entity.Utente;
import it.Camoia.SocialNetwork.Exception.BadUtenteException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SocialNetworkFrame extends JFrame {

    public SocialNetworkFrame(){
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        this.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(createTopPanel(), BorderLayout.NORTH);
        this.add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private JPanel createTopPanel(){
        topPanel = new JPanel();
        topPanel.setBorder(new TitledBorder(new EtchedBorder(), "Utility"));
        printButton = new JButton("Stampa Utenti");
        printButton.setPreferredSize(new Dimension(150, 80));
        printButton.setFont(new Font("monospace", Font.BOLD, 15));
        printButton.addActionListener(listener -> {
            try {
                List<Utente> listaUtenti = SocialNetworkGUI.socialManager.getUtenti();
                updateTextArea(listaUtenti);
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
                    throw new IllegalArgumentException();
                }
                Utente tmpUtente = new Utente(username, nome, cognome, eMail, password, nazione, data);
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
            SelectUtentePanel selectUtentePanel = null;
            try {
                selectUtentePanel = new SelectUtentePanel();
                int result = JOptionPane.showConfirmDialog(SocialNetworkFrame.this, selectUtentePanel, "Quale utente vuoi eliminare?", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION){
                    String username = (String) selectUtentePanel.utentiBox.getSelectedItem();
                    SocialNetworkGUI.socialManager.removeUtente(username);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Errore durante la Cancellazione", "Errore!", JOptionPane.ERROR_MESSAGE);
            }
        });

        modifyButton = new JButton("Modifica Utente");
        modifyButton.setPreferredSize(new Dimension(150, 80));
        modifyButton.setFont(new Font("monospace", Font.BOLD, 15));

        modifyButton.addActionListener(listener -> {
            try {
                SelectUtentePanel selectUtentePanel = new SelectUtentePanel();
                int result = JOptionPane.showConfirmDialog(SocialNetworkFrame.this, selectUtentePanel, "Quale utente vuoi modificare?", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION){
                    String username = (String) selectUtentePanel.utentiBox.getSelectedItem();
                    Utente utente = SocialNetworkGUI.socialManager.findUtente(username);
                    InsertUtentePanel insertPanel = new InsertUtentePanel();
                    insertPanel.usernameField.setText(utente.getUsername());
                    insertPanel.usernameField.setEditable(false);
                    insertPanel.nazioneField.setText(utente.getNazione());
                    insertPanel.nomeField.setText(utente.getNome());
                    insertPanel.cognomeField.setText(utente.getCognome());
                    insertPanel.passwordField.setText(utente.getPassword());
                    insertPanel.eMailField.setText(utente.geteMail());
                    insertPanel.dateField.setText(utente.getDataDiNascita());
                    int resultInsert = JOptionPane.showConfirmDialog(SocialNetworkFrame.this, insertPanel, "Modifica l'Utente: " + username, JOptionPane.OK_CANCEL_OPTION);
                        if (resultInsert == JOptionPane.OK_OPTION){
                            username = insertPanel.usernameField.getText();
                            String nome = insertPanel.nomeField.getText();
                            String cognome = insertPanel.cognomeField.getText();
                            String eMail = insertPanel.eMailField.getText();
                            String password = insertPanel.passwordField.getText();
                            String nazione = insertPanel.nazioneField.getText();
                            String data = insertPanel.dateField.getText();
                            if (!data.matches("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}")){
                                System.out.println("Ho letto: " + data);
                                throw new IllegalArgumentException();
                            }
                            Utente tmpUtente = new Utente(username, nome, cognome, eMail, password, nazione, data);
                            SocialNetworkGUI.socialManager.modificaUtente(tmpUtente);
                        }
                }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(this, "Errore durante la modifica", "Errore!", JOptionPane.ERROR_MESSAGE);
            } catch (BadUtenteException ex) {
                JOptionPane.showMessageDialog(this, "Errore parametri Utente!", "MODIFICA NON EFFETTUATA", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Formato data sbagliato, usare il seguente: \n YYYY-MM-DD", "MODIFICA NON EFFETTUATA", JOptionPane.ERROR_MESSAGE);
            }
        });

        filterButton = new JButton("Filtra Nazione");
        filterButton.setPreferredSize(new Dimension(150, 80));
        filterButton.setFont(new Font("monospace", Font.BOLD, 15));
        filterButton.addActionListener(listener -> {
            try {
                SelectNationPanel selectNationPanel = new SelectNationPanel();
                int result = JOptionPane.showConfirmDialog(SocialNetworkFrame.this, selectNationPanel, "Seleziona una Nazione", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION){
                    String nazione = (String) selectNationPanel.nationBox.getSelectedItem();
                    List<Utente> listaUtentiFiltered = SocialNetworkGUI.socialManager.getUtentiByNation(nazione);
                    updateTextArea(listaUtentiFiltered);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Errore durante la Stampa", "Errore!", JOptionPane.ERROR_MESSAGE);
            }
        });



        topPanel.add(printButton);
        topPanel.add(insertButton);
        topPanel.add(deleteButton);
        topPanel.add(modifyButton);
        topPanel.add(filterButton);
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

    public JPanel createBottomPanel(){
        bottomPanel = new JPanel(new GridLayout(1,1));
        JLabel creditLabel = new JLabel("Made by Andrea Camoia - 2023");
        creditLabel.setHorizontalAlignment(JLabel.CENTER);
        creditLabel.setPreferredSize(new Dimension(150, 40));
        creditLabel.setFont(new Font("monospace", Font.BOLD, 14));
        creditLabel.setBackground(Color.WHITE);
        creditLabel.setOpaque(true);
        bottomPanel.add(creditLabel);

        return bottomPanel;
    }



    private void updateTextArea(List<Utente> listaUtenti){
        textArea.setText("");
        textArea.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        textArea.append(String.format("%-20s   \t%-12s   \t%-12s   \t%-20s   \t%-20s\t\t   %-25s   \t%-15s", "USERNAME", "NOME", "COGNOME", "PASSWORD", "E-MAIL", "NAZIONALITÀ", "DATA DI NASCITA"));
        textArea.append("\n");
        textArea.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        //iterates over the list
        for(Utente tmpUtente: listaUtenti)
        {
            textArea.append(String.format("%-20s   \t%-12s   \t%-12s   \t%-20s   \t%-20s\t\t   %-25s   \t%-15s",
                    tmpUtente.getUsername(), tmpUtente.getNome(), tmpUtente.getCognome(), tmpUtente.getPassword(), tmpUtente.geteMail(), tmpUtente.getNazione(), tmpUtente.getDataDiNascita()));
            textArea.append("\n");
        }
        textArea.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }




    protected JPanel topPanel;
    protected JPanel centerPanel;
    protected JPanel bottomPanel;
    protected JButton printButton;
    protected JButton insertButton;
    protected JButton modifyButton;
    protected JButton filterButton;

    protected JButton deleteButton;
    protected TextArea textArea;
    public static final int FRAME_WIDTH = 1420;
    public static final int FRAME_HEIGHT = 720;
}
