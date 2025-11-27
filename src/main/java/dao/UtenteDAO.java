package dao;

import entity.Utente;

public interface UtenteDAO 
{
    public Utente getUserByCredentials(String email, String password) throws Exception;
	public void changePassword(int idUtente, String oldPassword, String newPassword) throws Exception;
}
