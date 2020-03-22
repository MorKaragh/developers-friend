package utils;

public class RuntimeExceptionWithLogging extends RuntimeException{

    public RuntimeExceptionWithLogging(String message) {
        super(message);
    }

    public RuntimeExceptionWithLogging(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeExceptionWithLogging(Throwable cause) {
        super(cause);
    }

}
