package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Progetto;
import dto.TeamDTO;

public class TeamWorkPostgresDAO implements TeamWorkDAO 
{

	@Override
	public boolean creaTeamWork(long idResponsabile, String nome) throws SQLException {
		Connection connection = PostgresConnection.connect();
		
		String query = 	"INSERT INTO \"Teamwork\" ( \"id_UtenteResponsabile\", nome ) "
				+ "VALUES (?, ?)";
		
		PreparedStatement st = connection.prepareStatement(query);
		st.setLong(1, idResponsabile);
		st.setString(2, nome);
		
		return st.executeUpdate() > 0;
	}

	@Override
	public ArrayList<TeamDTO> elencaTeamUtente(long idUtente) throws SQLException {
		Connection connection = PostgresConnection.connect();
		String query = 	  "SELECT \"id_Team\", nome "
						+ "FROM \"Teamwork\" WHERE \"id_UtenteResponsabile\" = ?";
		PreparedStatement st = connection.prepareStatement(query);
		ResultSet result = st.executeQuery();
		
		return creaElenco(result);
	}

	private ArrayList<TeamDTO> creaElenco(ResultSet result) throws SQLException {
		ArrayList<TeamDTO> elenco = new ArrayList<TeamDTO>();
		while(result.next())
		{
			elenco.add(new TeamDTO(
				result.getLong("id_Team"),
				result.getString("nome")
			));
		}
		return elenco;
	}

	@Override
	public boolean creaProgetto(long idProgetto, long idTeam) throws SQLException {
		Connection connection = PostgresConnection.connect();
		String query = "INSERT INTO \"TeamProgetto\" (\"id_progetto\", \"id_Team\") "
				+ "VALUES(?, ?)";
		PreparedStatement st = connection.prepareStatement(query); 
		st.setLong(1, idProgetto);
		st.setLong(2, idTeam);
		int rows = st.executeUpdate();
		return rows > 0;
	}

	@Override
	public boolean addUser(long idUtente, long idTeam) throws SQLException {
		Connection connection = PostgresConnection.connect();
		String query = "INSERT INTO \"PersonaleTeamwork\" (\"id_Utente\", \"id_Team\") "
				+ "VALUES(?, ?)";
		PreparedStatement st = connection.prepareStatement(query); 
		st.setLong(1, idUtente);
		st.setLong(2, idTeam);
		int rows = st.executeUpdate();
		return rows > 0;
	}
}
