package dao;

import utils.RuntimeExceptionWithLogging;

import java.io.IOException;

public class DaoException extends RuntimeExceptionWithLogging {

    public DaoException(Throwable cause) {
        super(cause);
    }

}
