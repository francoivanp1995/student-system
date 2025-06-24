package datos.Excepcion;

public class UsuarioExcepction extends Exception {
    public UsuarioExcepction() {

    }

    public UsuarioExcepction(String message) {
        super(message);
    }

    public UsuarioExcepction(Throwable cause) {
        super(cause);
    }

    public UsuarioExcepction(String message, Throwable cause) {
        super(message,cause);
    }
}
