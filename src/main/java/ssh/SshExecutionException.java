package ssh;

import utils.RuntimeExceptionWithLogging;

public class SshExecutionException extends RuntimeExceptionWithLogging {
    public SshExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
