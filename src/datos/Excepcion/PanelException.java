package datos.Excepcion;

public class PanelException extends Exception {

    public PanelException() {
    }

    public PanelException(String message) {
        super(message);
    }

    public PanelException(Throwable cause) {
        super(cause);
    }

    public PanelException(String message, Throwable cause) {
        super(message,cause);
    }
}
