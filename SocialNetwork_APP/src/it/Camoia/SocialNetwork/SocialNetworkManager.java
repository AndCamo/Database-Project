package it.Camoia.SocialNetwork;

import it.Camoia.SocialNetwork.Entity.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SocialNetworkManager {
    public SocialNetworkManager(){
        dbConnector = new DatabaseConnector();
    };


    public List<Utente> getUtenti() throws SQLException {
        dbConnector.startConnection();
        String username, nome, cognome, eMail, password, nazione, dataDiNascita;
        List<Utente> listUtenti = new ArrayList<>();
        ResultSet resultSet = dbConnector.getAllUtente();

        while (resultSet.next()){
            username = resultSet.getString("Username");
            nome = resultSet.getString("Nome");
            cognome = resultSet.getString("Cognome");
            eMail = resultSet.getString("eMail");
            nazione= resultSet.getString("Nazionalita");
            password = resultSet.getString("Access_Key");
            dataDiNascita = resultSet.getString("Data_Di_Nascita");
            Utente tmpUtente = new Utente(username, nome, cognome, eMail, password, nazione, dataDiNascita);

            listUtenti.add(tmpUtente);
        }

        dbConnector.closeConnection();

        return listUtenti;
    }

    public void insertUtente(Utente newUtente) throws SQLException {
        dbConnector.startConnection();
        String query = "INSERT INTO Utente VALUES " + "(" +
                '"' + newUtente.getUsername() + '"' + ',' + '"' + newUtente.getPassword() + '"' + ',' + '"' + newUtente.geteMail() + '"' + ','
                + '"' + newUtente.getNome() + '"' + ',' + '"' + newUtente.getCognome() + '"' + ','
                + '"' + newUtente.getDataDiNascita() + '"' + ',' + '"' + newUtente.getNazione() + '"' + ");";


        System.out.println(query);
        dbConnector.insertValues(query);

        dbConnector.closeConnection();
    }


    DatabaseConnector dbConnector;


}
