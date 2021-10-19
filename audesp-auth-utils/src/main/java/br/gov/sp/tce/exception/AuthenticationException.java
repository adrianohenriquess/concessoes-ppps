package br.gov.sp.tce.exception;

public class AuthenticationException extends Exception {
	
	private static final long serialVersionUID = -9070099739083415245L;
	
	protected int status = 403;

	public AuthenticationException(Throwable cause) {
		super(cause);
	}
	
	public AuthenticationException(String message) {
		super(message);
	}
	
	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public int getStatus() {
		return status;
	}
}
