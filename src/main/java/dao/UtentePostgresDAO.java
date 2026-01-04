package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CreaUtenteDTO;
import dto.UtenteInfoDTO;
import entity.Utente;
import service.HashCode256;
import service.TokenGenerator;

public class UtentePostgresDAO extends PostgresConnection implements UtenteDAO
{
	
	@Override
	public Utente getUserByCredentials(String email, String password) throws Exception
	{
		Connection connection = connect();
		String query = "SELECT * FROM \"Utente\" WHERE lower(\"email\")=lower(?) AND \"password\"=?";
		PreparedStatement st = connection.prepareStatement(query);
		st.setString(1, email);
		st.setString(2, password);
		
		ResultSet rs = st.executeQuery();
		if(!rs.next())
		{
			closeConnection(st, rs);
			verifyEmail(email, connection);
		}	
		
		Utente utente = new Utente(
				rs.getInt("id_Utente"),
				rs.getString("nome"),
				rs.getString("cognome"),
				email, password, 
				rs.getBoolean("admin"),
				new TokenGenerator(System.getenv("JWT_SECRET")).
					generateToken(rs.getInt("id_Utente"), rs.getBoolean("admin"))
			);
		closeConnection(connection, st, rs);
		return utente;
	}

	private void closeConnection(PreparedStatement st, ResultSet rs) throws SQLException {
		st.close();
		rs.close();
	}

	private void verifyEmail(String email, Connection connection) throws Exception {
		String query;
		
		//Scopri se l'utnte esiste
		query = "Select * from \"Utente\" where \"email\"=?";
		PreparedStatement st = connection.prepareStatement(query);
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		
		if(rs.next())
		{
			closeConnection(connection, st, rs);
			throw new Exception("Passord errata");
		}
		else 
		{
			closeConnection(connection, st, rs);
			throw new Exception("L'utente non esiste");
		}
	}

	@Override
	public void changePassword(int idUtente, String oldPassword, String newPassword) throws Exception
	{
	    Connection connection = connect();
	   
	    // Aggiorna la password
	    String updateQuery = "UPDATE \"Utente\" SET \"password\"=? WHERE \"id_Utente\"=?";
	    PreparedStatement updateSt = connection.prepareStatement(updateQuery);
	    updateSt.setString(1, newPassword);
	    updateSt.setInt(2, idUtente);
	    
	    int rowsAffected = updateSt.executeUpdate();
	    
	    if(rowsAffected == 0)	//L'utente non esiste
	    {
	        updateSt.close();
	        connection.close();
	        throw new Exception("Utente non trovato");
	    }
	    
	    updateSt.close();
	    connection.close();
	}

	private void closeConnection(Connection connection, PreparedStatement st, ResultSet rs) throws SQLException {
		connection.close();
		st.close();
		rs.close();
	}

	@Override
	public ArrayList<UtenteInfoDTO> getElencoUteniByIdProgetto(Long idProgetto) throws SQLException {
		Connection connection = connect();
		String query = "Select  u.\"idUtente\", u.\"email\", u.nome, u.cognome "
				+ "FROM \"TeamProgetto\"AS p "
				+ "NATURAL JOIN \"PersonaleTeamwork\" AS t "
				+ "NATURAL JOIN \"Utente AS u "
				+ "WHERE p.\"id_progetto\" = ?";
		PreparedStatement st = connection.prepareStatement(query);
		st.setLong(1, idProgetto);
		
		ResultSet rs = st.executeQuery();
		ArrayList<UtenteInfoDTO> elenco = new ArrayList<UtenteInfoDTO>();
		return creaElencoInfoUtenti(rs);
	}

	public ArrayList<UtenteInfoDTO> creaElencoInfoUtenti(ResultSet rs) throws SQLException {
		ArrayList<UtenteInfoDTO> elenco = new ArrayList<UtenteInfoDTO>();
		while(rs.next())
		{
			elenco.add(new UtenteInfoDTO(
				rs.getLong("idUtente"),
				rs.getString("email"),
				rs.getString("nome"),
				rs.getString("cognome")
			));
		}
		return elenco;
	}

	public boolean createUser(CreaUtenteDTO utente) throws SQLException {
		Connection connection = PostgresConnection.connect();
		String query = "INSERT INTO \"Utente\" (nome, cognome, email, admin, password)";
		PreparedStatement st = connection.prepareStatement(query);
		
		int rowsAffected = st.executeUpdate();
		return rowsAffected > 0;
	}
}

