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

    public final void insertValues(String query) throws SQLException {
        stmt.execute(query);
        System.out.println("QUERY ESEGUITA");
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
