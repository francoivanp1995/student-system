package datos.Excepcion;

public class IniciarException extends RuntimeException {
    public IniciarException() {

    }
    public IniciarException(String message) {
        super(message);
    }

    public IniciarException(Throwable cause) {
        super(cause);
    }

    public IniciarException(String message, Throwable cause) {
        super(message,cause);
    }
}
