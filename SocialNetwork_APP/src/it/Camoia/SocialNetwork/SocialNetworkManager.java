package it.Camoia.SocialNetwork;

import it.Camoia.SocialNetwork.Entity.Utente;
import it.Camoia.SocialNetwork.Exception.BadUtenteException;

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

    public List<Utente> getUtentiByNation(String nazioneUtente) throws SQLException {
        dbConnector.startConnection();
        String username, nome, cognome, eMail, password, nazione, dataDiNascita;
        List<Utente> listUtenti = new ArrayList<>();
        ResultSet resultSet = dbConnector.getUtentiByNation(nazioneUtente);

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

    public void insertUtente(Utente newUtente) throws SQLException, BadUtenteException {
        dbConnector.startConnection();
        if (newUtente.checkUtente()) {
            dbConnector.insertValues(newUtente);
        } else {
            throw new BadUtenteException();
        }
        dbConnector.closeConnection();
    }

    public void removeUtente(String username) throws SQLException {
        dbConnector.startConnection();
        dbConnector.removeValues(username, "Utente");
        dbConnector.closeConnection();
    }

    public void modificaUtente(Utente newUtente) throws SQLException, BadUtenteException {
        dbConnector.startConnection();
        if (newUtente.checkUtente()) {
            dbConnector.editUtente(newUtente);
        } else {
            throw new BadUtenteException();
        }
        dbConnector.closeConnection();
    }

    public Utente findUtente(String username) throws SQLException{
        List<Utente> utenti = this.getUtenti();
        Utente utenteToReturn = null;
        for (Utente tmpUtente : utenti){
            if (tmpUtente.getUsername().equals(username))
                utenteToReturn = tmpUtente;
        }

        return utenteToReturn;
    }


    DatabaseConnector dbConnector;


}
