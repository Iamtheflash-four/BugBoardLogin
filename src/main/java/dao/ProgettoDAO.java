package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Progetto;

public interface ProgettoDAO
{
	public ArrayList<Progetto> elencaProgettiUtente(int idUtente) throws Exception;

	public long creaProgetto(String nomeProgetto) throws Exception;
}
