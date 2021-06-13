package me.superbiebel.punishapi.exceptions;

public class FailedServiceOperationException extends Exception{
    public FailedServiceOperationException() {
        super();
    }
    
    public FailedServiceOperationException(String message) {
        super(message);
    }
    
    public FailedServiceOperationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FailedServiceOperationException(Throwable cause) {
        super(cause);
    }
    
    public FailedServiceOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
