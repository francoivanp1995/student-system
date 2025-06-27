package datos.Excepcion;

public class ControladorException extends RuntimeException {
  public ControladorException() {

  }

  public ControladorException(String message) {
    super(message);
  }

  public ControladorException(Throwable cause) {
    super(cause);
  }

  public ControladorException(String message, Throwable cause) {
    super(message,cause);
  }
}
