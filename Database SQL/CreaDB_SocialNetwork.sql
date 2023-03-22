drop database if exists SocialNetwork;
create database SocialNetwork;
use SocialNetwork;

-- Utente(Username, Password, eMail, Nome, Cognome, DataDiNascita, Nazionalità);
CREATE TABLE Utente(
	Username char(30) not null,
    Access_Key char(30) not null, 
    eMail char(50) not null,
    Nome char(30) not null,
    Cognome char(30) not null,
    Data_Di_Nascita date not null, 
    Nazionalita char(30) not null,
    PRIMARY KEY(Username)
);
-- Post(ID, Descrizione, Numero_Commenti, Username_Utente, Data_Pubblicazione);
CREATE TABLE Post(
	ID int not null, 
    Descrizione tinytext not null,
    Numero_Commenti int not null, 
    Username_Utente char(30) not null,
    Data_Pubblicazione datetime not null,
    FOREIGN KEY  (Username_Utente) REFERENCES Utente(Username) ON DELETE CASCADE,
    PRIMARY KEY(ID)
);

-- Multimedia(ID_Post, Nome, Dimensione, Tipo);
CREATE TABLE Multimedia (
	ID_Post int not null,
    Nome char(50) not null,
    Dimensione double not null,
    Tipo char(50) not null,
    FOREIGN KEY (ID_Post) REFERENCES Post(ID) ON DELETE CASCADE,
    PRIMARY KEY(ID_Post, Nome)
);

-- Commento(ID_Post, Numero, Contenuto, Username_Utente);
CREATE TABLE Commento(
	ID_Post int not null,
    Numero int not null,
    Contenuto tinytext not null,
    Username_Utente char(30) not null,
	FOREIGN KEY (ID_Post) REFERENCES Post(ID) ON DELETE CASCADE,
    FOREIGN KEY (Username_Utente) REFERENCES Utente(Username) ON DELETE CASCADE,
    PRIMARY KEY (ID_Post, Numero)
);

-- Gruppo(Nome, Data_Creazione, Numero_Partecipanti);
CREATE TABLE Gruppo(
	Nome char(50) not null,
    Data_Creazione date not null, 
    Numero_Partecipanti int not null,
    PRIMARY KEY(Nome)
);

-- Partecipazione(Username_Utente, Nome_Gruppo);
CREATE TABLE Partecipazione (
	Username_Utente char(30) not null,
	Nome_Gruppo char(50) not null,
	FOREIGN KEY (Username_Utente) REFERENCES Utente(Username) ON DELETE CASCADE,
    FOREIGN KEY (Nome_Gruppo) REFERENCES Gruppo(Nome) ON DELETE CASCADE,
    PRIMARY KEY(Username_Utente, Nome_Gruppo)
);

-- Argomento(Nome);
CREATE TABLE Argomento(
	Nome char(40) not null,
    PRIMARY KEY(Nome)
);

-- Riguardare(Nome_Gruppo, Nome_Argomento);
CREATE TABLE Riguardare(
	Nome_Gruppo char(50) not null,
	Nome_Argomento char(40) not null,
    FOREIGN KEY (Nome_Gruppo) REFERENCES Gruppo(Nome) ON DELETE CASCADE,
    FOREIGN KEY (Nome_Argomento) REFERENCES Argomento(Nome) ON DELETE CASCADE
);

-- Azienda(P_IVA, Ragione_Sociale, Telefono);
CREATE TABLE Azienda(
	P_IVA int not null,
    Ragione_Sociale char(50) not null,
    Telefono int not null,
    PRIMARY KEY(P_IVA)
);

-- Pagina(Nome, Lingua, Categoria, Numero di Partecipanti , PIVA_Azienda);
CREATE TABLE Pagina(
    Nome char(50) not null,
    Lingua char(50) not null,
    Categoria char(50) not null,
    Numero_Partecipanti int not null,
    PIVA_Azienda int not null,
    FOREIGN KEY (PIVA_Azienda) REFERENCES Azienda(P_IVA) ON DELETE CASCADE,
    PRIMARY KEY (Nome)
);

-- Seguire(Username_Utente, Nome_Pagina);
CREATE TABLE Seguire(
    Username_Utente char(30) not null,
    Nome_Pagina char(50) not null,
    FOREIGN KEY (Nome_Pagina) REFERENCES Pagina(Nome) ON DELETE CASCADE,
    FOREIGN KEY (Username_Utente) REFERENCES Utente(Username) ON DELETE CASCADE,
    PRIMARY KEY (Username_Utente, Nome_Pagina)
);
