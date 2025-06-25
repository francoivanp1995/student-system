package datos.Excepcion;

public class PresentacionException extends RuntimeException {
    public PresentacionException() {
    }

    public PresentacionException(String message) {
        super(message);
    }

    public PresentacionException(Throwable cause) {
        super(cause);
    }

    public PresentacionException(String message, Throwable cause) {
        super(message,cause);
    }
}
