package entity;

public class Utente 
{
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String token;
	
	public Utente(String nome, String cognome, String email, String password, String token) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.token = token;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getToken() {
		return token;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String toJSON()
	{
		return "{" + 
				"\"nome\":" + nome +  "," + 
				"\"cognome\":" + cognome + "," + 
				"\"email\":" + email + "," + 
				"\"password\":" + password + "," + 
				"\"token\":" + token + 
				"}";
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", password=" + password
				+ ", token=" + token + "]";
	}
}
