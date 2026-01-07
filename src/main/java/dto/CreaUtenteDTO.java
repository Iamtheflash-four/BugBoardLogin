package dto;

public class CreaUtenteDTO {
	private String nome;
	private String cognome;
	private String email;
	private boolean admin;
	private String password;

	public CreaUtenteDTO() {}
	
	public CreaUtenteDTO(String nome, String cognome, String email, boolean admin, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.admin = admin;
		this.password = password;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}