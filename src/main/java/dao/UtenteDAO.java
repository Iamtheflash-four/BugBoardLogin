package dao;

import entity.Utente;

public interface UtenteDAO 
{
    Utente getUserByCredentials(String email, String password) throws Exception;
    void changePassword(String email, String oldPassword, String newPassword) throws Exception;
}
