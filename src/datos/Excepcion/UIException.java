package datos.Excepcion;

public class UIException extends Exception {
    public UIException() {

    }

    public UIException(String message) {
        super(message);
    }

    public UIException(Throwable cause) {
        super(cause);
    }

    public UIException(String message, Throwable cause) {
        super(message,cause);
    }
}
