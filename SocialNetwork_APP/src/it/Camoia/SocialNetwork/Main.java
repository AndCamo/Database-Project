package it.Camoia.SocialNetwork;

import it.Camoia.SocialNetwork.Entity.Utente;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        SocialNetworkManager socialManager = new SocialNetworkManager();

        List<Utente> utenteList = socialManager.getUtenti();

        for (Utente tmpUtente : utenteList){
            System.out.println(tmpUtente);
        }
    }
}