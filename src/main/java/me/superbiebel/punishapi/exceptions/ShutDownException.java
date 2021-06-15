package me.superbiebel.punishapi.exceptions;

public class ShutDownException extends Exception {
    public ShutDownException() {
        super();
    }
    
    public ShutDownException(String message) {
        super(message);
    }
    
    public ShutDownException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ShutDownException(Throwable cause) {
        super(cause);
    }
    
    public ShutDownException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
