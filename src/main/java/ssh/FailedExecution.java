package ssh;

import utils.RuntimeExceptionWithLogging;

public class FailedExecution extends RuntimeExceptionWithLogging {
    public FailedExecution(String message, Throwable cause) {
        super(message, cause);
    }
}
