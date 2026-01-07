package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.UtenteInfoDTO;
import entity.Utente;

public interface UtenteDAO 
{
    public Utente getUserByCredentials(String email, String password) throws Exception;
	
    public void changePassword(int idUtente, String oldPassword, String newPassword) throws Exception;

    public ArrayList<UtenteInfoDTO> getElencoUteniByIdProgetto(Long idProgetto) throws Exception;

	public long getUserID(String email) throws Exception; 
}
