package me.superbiebel.punishapi.exceptions;

public class ServiceNotFoundException extends Exception {
    public ServiceNotFoundException() {
        super();
    }
    
    public ServiceNotFoundException(String message) {
        super(message);
    }
    
    public ServiceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ServiceNotFoundException(Throwable cause) {
        super(cause);
    }
    
    public ServiceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
