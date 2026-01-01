package dto;

public class UtenteInfoDTO 
{
	private long idUtente;
	private String email;
	private String nome;
	private String cognome;
	
	public UtenteInfoDTO(long idUtente, String email, String nome, String cognome) {
		super();
		this.idUtente = idUtente;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
	}

	public long getIdUtente() {
		return idUtente;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setIdUtente(long idUtente) {
		this.idUtente = idUtente;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
}
