package datos.Excepcion;

public class TablaException extends Exception {
    public TablaException() {

    }

    public TablaException(String message) {
        super(message);
    }

    public TablaException(Throwable cause) {
        super(cause);
    }

    public TablaException(String message, Throwable cause) {
        super(message,cause);
    }
}
