package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Utente;
import service.TokenGenerator;

public class UtentePostgresDAO extends PostgresConnection implements UtenteDAO
{
	
	@Override
	public Utente getUserByCredentials(String email, String password) throws Exception
	{
		Connection connection = connect();
		String query = "Select * from \"Utente\" where \"email\"=? AND \"password\"=?";
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
				rs.getInt("idUtente"),
				rs.getString("nome"),
				rs.getString("cognome"),
				email, password,
				new TokenGenerator(System.getenv("JWT_SECRET")).generateToken(email)
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

	private void closeConnection(Connection connection, PreparedStatement st, ResultSet rs) throws SQLException {
		connection.close();
		st.close();
		rs.close();
	}
}
