package exceptions;

public class NoMatchException extends RuntimeException {
  public NoMatchException(String message) {
    super(message);
  }
}
