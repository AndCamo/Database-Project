package it.Camoia.SocialNetwork.GUI;

import it.Camoia.SocialNetwork.Entity.Utente;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectNationPanel extends JPanel {
    public SelectNationPanel() throws SQLException {
        this.setLayout(new GridLayout(2, 1));
        JLabel choiseLabel = new JLabel("Seleziona la Nazione da filtrare:");
        List<Utente> utentiList = SocialNetworkGUI.socialManager.getUtenti();
        ArrayList<String> nazioniUtenti = new ArrayList<>();
        utentiList.forEach(utente -> {
            if (!nazioniUtenti.contains(utente.getNazione()))
                    nazioniUtenti.add(utente.getNazione());
        });
        nationBox = new JComboBox<String>();
        nazioniUtenti.forEach(nazione -> nationBox.addItem(nazione));

        this.add(choiseLabel);
        this.add(nationBox);
    }

    protected JComboBox nationBox;
}
