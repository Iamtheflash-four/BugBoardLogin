package dto;

import entity.Utente;

public class LoginResponse 
{
	private boolean success;
    private String message;
    private Utente utente;
    
    public LoginResponse(boolean success, String message, Utente utente) {
		super();
		this.success = success;
		this.message = message;
		this.utente = utente;
	}

	public LoginResponse() {}
	
	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
}
