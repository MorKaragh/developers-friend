package config;

import java.io.IOException;

public class InitializationException extends RuntimeException {

    public InitializationException(String s) {
        super(s);
    }

    public InitializationException(String s, IOException e) {
        super(s,e);
    }
}
