-- 1. Selezionare gli Utenti Italiani o Statunitensi nati dopo il 2000, ordinandoli per cognome.

SELECT u.Username, u.Nome, u.Cognome
FROM Utente u
WHERE (u.Nazionalita = "Italia" or u.Nazionalita = "USA") and YEAR(u.Data_Di_Nascita) > 2000
ORDER BY u.Cognome;

-- 2. Elencare tutti i gruppi riguardanti i videogiochi. (Nome)

SELECT g.Nome
FROM Gruppo g, Argomento a, Riguardare r
WHERE a.Nome = "Videogame" and g.Nome = r.Nome_Gruppo and a.Nome = r.Nome_Argomento;

-- 3. Visualizzare la somma della dimensione di tutti i contenuti multimediali. (DimensioneTotale)

SELECT sum(Dimensione) as DimensioneTotale
FROM Multimedia;

-- 4. Contare il numero di post pubblicato da ogni utente.

SELECT u.Username, count(p.ID) as NumeroPost
FROM Utente u, Post p
where u.Username = p.Username_Utente
group by u.Username;

-- 5. Contare il numero di post pubblicato da ogni utente minorenne.

SELECT u.Username, count(p.ID) as NumeroPost
FROM Utente u, Post p
where u.Username = p.Username_Utente and YEAR(u.Data_Di_Nascita) > 2004
group by u.Username;

-- 6. Elencare la tipologia di contenuti multimediali la cui somma delle dimensioni più alta (Tipo, DimensioneTotale).
drop view if exists Tipo_Dimensioni;

create view Tipo_Dimensioni as
SELECT Multimedia.Tipo, sum(Dimensione) as DimensioneTotale
FROM Multimedia
GROUP BY Multimedia.Tipo;

SELECT Tipo, DimensioneTotale
FROM Tipo_Dimensioni
WHERE DimensioneTotale = (select max(DimensioneTotale) from Tipo_Dimensioni);

-- 7. Selezione tutti gli utenti che non seguono pagine di sport (Username, Nome, Cognome).

SELECT u.Username, u.Nome, u.Cognome
FROM Utente u
WHERE u.Username NOT IN (	SELECT u.Username
							FROM Utente u, Pagina p, Seguire s
							WHERE u.Username = s.Username_Utente and p.Nome = s.Nome_Pagina and p.Categoria = "Sport");

-- 8. Elencare gli utenti che seguono tutte le pagine di Automobili (Username, Nome, Data_Di_Nascita).
SELECT u.Username, u.Nome, u.Data_Di_Nascita
FROM Utente u
WHERE NOT EXISTS (	SELECT *
					FROM Pagina p
                    WHERE p.Categoria = "Automobili" and NOT EXISTS ( 	SELECT *
																		FROM Seguire s
																		WHERE u.Username = s.Username_Utente and p.Nome = s.Nome_Pagina));
 
