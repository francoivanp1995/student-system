package datos.Excepcion;

public class CredencialesInvalidaException extends RuntimeException {
    public CredencialesInvalidaException() {

    }

    public CredencialesInvalidaException(String message) {
        super(message);
    }

    public CredencialesInvalidaException(Throwable cause) {
        super(cause);
    }

    public CredencialesInvalidaException(String message, Throwable cause) {
        super(message,cause);
    }
}
