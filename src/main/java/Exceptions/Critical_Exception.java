package Exceptions;

public class Critical_Exception extends RuntimeException {
  public Critical_Exception() {
    super();
  }

  public Critical_Exception(String message, Throwable cause) {
    super(message, cause);
  }

  public Critical_Exception(String message) {
    super(message);
  }
}
