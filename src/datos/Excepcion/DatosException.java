package datos.Excepcion;

public class DatosException extends RuntimeException {
    public DatosException() {
    }

    public DatosException(String message) {
        super(message);
    }

    public DatosException(Throwable cause) {
        super(cause);
    }

    public DatosException(String message, Throwable cause) {
        super(message,cause);
    }
}
