package engine.dao;

import utils.RuntimeExceptionWithLogging;

public class DaoException extends RuntimeExceptionWithLogging {

    public DaoException(Throwable cause) {
        super(cause);
    }

}
