package it.Camoia.SocialNetwork.GUI;

import it.Camoia.SocialNetwork.Entity.Utente;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectUtentePanel extends JPanel {

    public SelectUtentePanel() throws SQLException {

        this.setLayout(new GridLayout(2, 1));
        JLabel choiseLabel = new JLabel("Seleziona l'Utente:");
        List<Utente> utentiList = SocialNetworkGUI.socialManager.getUtenti();
        ArrayList<String> usernameUtenti = new ArrayList<>();
        utentiList.forEach(utente -> usernameUtenti.add(utente.getUsername()));

        usernameUtenti.sort((utente1, utente2) -> {  //VISUALIZATION IN ALPHABETIC ORDER
            return utente1.compareToIgnoreCase(utente2);
        });
        utentiBox = new JComboBox<String>();
        usernameUtenti.forEach(username -> utentiBox.addItem(username));
        this.add(choiseLabel);
        this.add(utentiBox);
    }


    protected JComboBox<String> utentiBox;

}
