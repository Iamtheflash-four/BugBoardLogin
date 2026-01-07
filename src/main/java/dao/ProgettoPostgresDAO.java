package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Progetto;

public class ProgettoPostgresDAO extends PostgresConnection implements ProgettoDAO
{
	@Override
	public ArrayList<Progetto> elencaProgettiUtente(int idUtente) throws SQLException {
		Connection connection = connect();
		String query =    "Select \"id_progetto\", \"nomeprogetto\" "
						+ "from \"Utente\" natural join \"PersonaleTeamwork\" "
						+ "natural join \"TeamProgetto\" natural join \"Progetto\" "
						+ "where \"id_Utente\" = ?";
		PreparedStatement st = connection.prepareStatement(query);
		st.setInt(1, idUtente);
		ResultSet rs = st.executeQuery();
		
		ArrayList<Progetto> elenco = new ArrayList<Progetto>();
		System.out.println("Progetti: ");

		while (rs.next())
		{
			elenco.add(new Progetto(rs.getInt("id_progetto"), rs.getString("nomeprogetto")));
			System.out.println(rs.getString("nomeprogetto"));
		}
		return elenco;
	}
	
	@Override
	public long creaProgetto(String nomeProgetto) throws SQLException {
		Connection connection = connect();
		String query = "INSERT INTO \"Progetto\" (nomeprogetto) VALUES(?)";
		PreparedStatement st = connection.prepareStatement( query, PreparedStatement.RETURN_GENERATED_KEYS); 
		
		int rows = st.executeUpdate();
		if (rows <= 0)
			throw new SQLException("Errore: nessun progetto creato");
		ResultSet rs = st.getGeneratedKeys();	
		if (rs.next()) 
		    return rs.getLong(1); 		
		throw new SQLException("Errore: nessun progetto creato");
	}
}
