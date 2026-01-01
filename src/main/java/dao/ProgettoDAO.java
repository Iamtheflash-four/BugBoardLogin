package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Progetto;

public interface ProgettoDAO
{
	public ArrayList<Progetto> elencaProgettiUtente(int idUtente) throws SQLException;
}
