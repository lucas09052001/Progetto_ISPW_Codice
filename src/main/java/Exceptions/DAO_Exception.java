package Exceptions;

public class DAO_Exception extends Exception {

    public DAO_Exception() {
        super();
    }

    public DAO_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public DAO_Exception(String message) {
        super(message);
    }
}
