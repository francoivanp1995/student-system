package datos.Excepcion;

public class CursoException extends RuntimeException {
    public CursoException() {

    }

    public CursoException(String message) {
        super(message);
    }

    public CursoException(Throwable cause) {
        super(cause);
    }

    public CursoException(String message, Throwable cause) {
        super(message,cause);
    }
}
