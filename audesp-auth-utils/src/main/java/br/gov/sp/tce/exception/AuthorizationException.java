package br.gov.sp.tce.exception;

public class AuthorizationException extends Exception {
	
	private static final long serialVersionUID = -9070099739083415245L;
	
	protected int status = 401;

	public AuthorizationException(Throwable cause) {
		super(cause);
	}
	
	public AuthorizationException(String message) {
		super(message);
	}
	
	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public int getStatus() {
		return status;
	}
}
