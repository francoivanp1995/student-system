package datos.Excepcion;

public class ErrorOperacionException extends Exception {

	public ErrorOperacionException() {
		
	}
	
	public ErrorOperacionException(String message) {
		super(message);
	}
	
	public ErrorOperacionException(Throwable cause) {
		super(cause);
	}
	
	public ErrorOperacionException(String message, Throwable cause) {
		super(message,cause);
	}
}
