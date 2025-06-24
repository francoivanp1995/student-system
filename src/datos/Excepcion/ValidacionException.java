package datos.Excepcion;

public class ValidacionException extends Exception {

	public ValidacionException() {
		
	}
	
	public ValidacionException(String message) {
		super(message);
	}
	
	public ValidacionException(Throwable cause) {
		super(cause);
	}
	
	public ValidacionException(String message, Throwable cause) {
		super(message,cause);
	}
}
