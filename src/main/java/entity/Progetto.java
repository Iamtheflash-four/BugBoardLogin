package entity;

public class Progetto 
{
	private int idProgetto;
	private String nome;
	
	public Progetto(int idProgetto, String nome) {
		super();
		this.idProgetto = idProgetto;
		this.nome = nome;
	}
	public Progetto() {
		super();
	}
	public int getIdProgetto() {
		return idProgetto;
	}
	public String getNome() {
		return nome;
	}
	public void setIdProgetto(int idProgetto) {
		this.idProgetto = idProgetto;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
