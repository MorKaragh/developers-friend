package dao;

import utils.RuntimeExceptionWithLogging;

import java.io.IOException;

public class DaoFailed extends RuntimeExceptionWithLogging {

    public DaoFailed(Throwable cause) {
        super(cause);
    }

}
