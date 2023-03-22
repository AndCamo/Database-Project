package it.Camoia.SocialNetwork;

import it.Camoia.SocialNetwork.Entity.Utente;

import java.sql.*;


public class DatabaseConnector {
    static final String DB_URL = "jdbc:mysql://localhost:3306/SocialNetwork";
    static final String USER = "root";
    static final String PASS = "Andsql10";

    private static Connection conn = null;
    private static Statement stmt = null;

    public  void startConnection(){
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public final ResultSet getAllUtente(){
        ResultSet resultSet = null;
        try{
            resultSet = stmt.executeQuery("SELECT * FROM Utente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public final void insertValues(Utente newUtente) throws SQLException {
        String query = "INSERT INTO Utente VALUES " + "(" +
                '"' + newUtente.getUsername() + '"' + ',' + '"' + newUtente.getPassword() + '"' + ',' + '"' + newUtente.geteMail() + '"' + ','
                + '"' + newUtente.getNome() + '"' + ',' + '"' + newUtente.getCognome() + '"' + ','
                + '"' + newUtente.getDataDiNascita() + '"' + ',' + '"' + newUtente.getNazione() + '"' + ");";
        stmt.execute(query);
        System.out.println("INSERIMENTO ESEGUITO"); //Stampa di controllo
    }

    public final void editUtente(Utente utente) throws SQLException{
        String query = "UPDATE Utente SET" + "\n" +
                "Nome = " + '"' + utente.getNome() + '"' + ", Cognome = " + '"' + utente.getCognome() + '"' +
                ", Nazionalita = " + '"' + utente.getNazione() + '"' + ", eMail = " + '"' + utente.geteMail() + '"' +
                ", Access_Key = " + '"' + utente.getPassword() + '"' + ", Data_Di_Nascita = " + '"' + utente.getDataDiNascita() + '"' + "\n" +
                "WHERE Username = " + '"' + utente.getUsername() + '"';
        System.out.println(query);
        stmt.execute(query);
        System.out.println("MODIFICA ESEGUITA"); //Stampa di controllo
    }

    public final void removeValues(String username, String table) throws SQLException {
        String query = "DELETE FROM " + table + " WHERE Username = \"" + username + "\";";
        stmt.execute(query);
        System.out.println("RIMOZIONE ESEGUITA"); //Stampa di controllo
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
