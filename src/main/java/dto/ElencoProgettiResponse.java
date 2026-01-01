package dto;

import java.util.ArrayList;

import entity.Progetto;

public class ElencoProgettiResponse 
{
	String message;
	ArrayList<Progetto> elencoProgetti;
	
	public ElencoProgettiResponse(String message, ArrayList<Progetto> elencoProgetti) {
		super();
		this.message = message;
		this.elencoProgetti = elencoProgetti;
	}

	public ElencoProgettiResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public ArrayList<Progetto> getElencoProgetti() {
		return elencoProgetti;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setElencoProgetti(ArrayList<Progetto> elencoProgetti) {
		this.elencoProgetti = elencoProgetti;
	}
}