package entity;

public class Utente 
{
	private int idUtente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private boolean amministratore;
	private String token;
	
	public Utente(int idUtente, String nome, String cognome, String email, String password, boolean amministratore, String token) {
		super();
		this.idUtente = idUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.token = token;
		this.amministratore = amministratore;
	}

	public Utente() {}
	
	public boolean isAmministratore() {
		return amministratore;
	}

	public void setAmministratore(boolean amministratore) {
		this.amministratore = amministratore;
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

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", password=" + password
				+ ", token=" + token + "]";
	}
}