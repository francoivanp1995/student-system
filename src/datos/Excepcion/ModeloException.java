package datos.Excepcion;

public class ModeloException extends RuntimeException {
    public ModeloException() {

    }

    public ModeloException(String message) {
        super(message);
    }

    public ModeloException(Throwable cause) {
        super(cause);
    }

    public ModeloException(String message, Throwable cause) {
        super(message,cause);
    }
}
