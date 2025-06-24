package exceptions;

public class CriticalException extends RuntimeException {
  public CriticalException() {
    super();
  }

  public CriticalException(String message, Throwable cause) {
    super(message, cause);
  }

  public CriticalException(String message) {
    super(message);
  }
}
