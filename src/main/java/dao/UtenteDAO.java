package dao;

import java.sql.SQLException;

import entity.Utente;

public interface UtenteDAO 
{
	public Utente getUserByCredentials(String email, String password) throws Exception;
	
	//public java.sql.Connection connect();
}
