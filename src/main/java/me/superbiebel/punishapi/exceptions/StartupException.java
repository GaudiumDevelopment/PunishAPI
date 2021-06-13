package me.superbiebel.punishapi.exceptions;

public class StartupException extends Exception {
    public StartupException() {
        super();
    }
    
    public StartupException(String message) {
        super(message);
    }
    
    public StartupException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public StartupException(Throwable cause) {
        super(cause);
    }
    
    public StartupException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
