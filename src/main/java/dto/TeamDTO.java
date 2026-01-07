package dto;

public class TeamDTO 
{
	private long idTeam;
	private String nome;
	
	public TeamDTO(long idTeam, String nome) {
		super();
		this.idTeam = idTeam;
		this.nome = nome;
	}
	
	public TeamDTO() {}

	public long getIdTeam() {
		return idTeam;
	}

	public String getNome() {
		return nome;
	}

	public void setIdTeam(long idTeam) {
		this.idTeam = idTeam;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
