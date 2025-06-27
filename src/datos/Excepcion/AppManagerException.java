package datos.Excepcion;

public class AppManagerException extends RuntimeException {
    public AppManagerException() {

    }

    public AppManagerException(String message) {
        super(message);
    }

    public AppManagerException(Throwable cause) {
        super(cause);
    }

    public AppManagerException(String message, Throwable cause) {
        super(message,cause);
    }
}
