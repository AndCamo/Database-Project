package it.Camoia.SocialNetwork.Exception;

public class BadUtenteException extends Exception{
    public BadUtenteException(){
        super("Formato dati utente Errato!");
    }

    public BadUtenteException(String str){
        super(str);
    }
}
