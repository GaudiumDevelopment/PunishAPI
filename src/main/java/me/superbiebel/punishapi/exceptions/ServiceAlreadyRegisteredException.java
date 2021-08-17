package me.superbiebel.punishapi.exceptions;

/**
 * If a service is already registered.
 */
public class ServiceAlreadyRegisteredException extends Exception {
    public ServiceAlreadyRegisteredException() {
        super();
    }

    public ServiceAlreadyRegisteredException(String message) {
        super(message);
    }

    public ServiceAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }

    public ServiceAlreadyRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
