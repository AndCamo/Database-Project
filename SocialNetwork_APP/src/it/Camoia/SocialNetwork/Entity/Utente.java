package it.Camoia.SocialNetwork.Entity;

import it.Camoia.SocialNetwork.Exception.BadUtenteException;

import java.util.GregorianCalendar;

public class Utente {

    public Utente(String username, String nome, String cognome, String eMail, String password ,String nazione, String data){
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.eMail = eMail;
        this.nazione = nazione;
        this.password = password;
        String[] dateValues = data.split("-");
        this.dataDiNascita = new GregorianCalendar(Integer.parseInt(dateValues[0]), Integer.parseInt(dateValues[1]) - 1, Integer.parseInt(dateValues[2]) - 1);
    }


    public String getUsername() {
        return username;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String geteMail() {
        return eMail;
    }

    public String getNazione() {
        return nazione;
    }

    public String getDataDiNascita() {

        return (dataDiNascita.get(GregorianCalendar.YEAR)
                + "-" + (dataDiNascita.get(GregorianCalendar.MONTH) + 1)
                + "-" + (dataDiNascita.get(GregorianCalendar.DAY_OF_MONTH) + 1));
    }

    public String getPassword() {
        return password;
    }

    public boolean checkUtente(){
        boolean flag = true;
        if (nome.isEmpty() || cognome.isEmpty() || username.isEmpty()
                || eMail.isEmpty() || password.isEmpty() || dataDiNascita == null)
            flag = false;
        GregorianCalendar today = new GregorianCalendar();
        if (today.get(GregorianCalendar.YEAR) - dataDiNascita.get(GregorianCalendar.YEAR) < 18){
            if (today.get(GregorianCalendar.YEAR) - dataDiNascita.get(GregorianCalendar.YEAR) == 17){
                if (today.get(GregorianCalendar.DAY_OF_YEAR) > today.get(GregorianCalendar.DAY_OF_YEAR))
                    flag = false;
            }
            else {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public String toString() {
        String dataString = ((dataDiNascita.get(GregorianCalendar.DAY_OF_MONTH) + 1)
                + "/" + (dataDiNascita.get(GregorianCalendar.MONTH) + 1)
                + "/" + dataDiNascita.get(GregorianCalendar.YEAR));
        return (username + " - " + nome + " - " + cognome + " - " + eMail + " - " + password + " - " + nazione + " - " + dataString);
    }

    private String username;
    private String nome;
    private String cognome;
    private String eMail;
    private String nazione;
    private String password;
    private GregorianCalendar dataDiNascita;
}
