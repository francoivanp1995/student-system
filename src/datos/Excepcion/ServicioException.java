package datos.Excepcion;

public class ServicioException extends RuntimeException {
  public ServicioException() {
  }

  public ServicioException(String message) {
    super(message);
  }

  public ServicioException(Throwable cause) {
    super(cause);
  }

  public ServicioException(String message, Throwable cause) {
    super(message,cause);
  }
}
