package me.superbiebel.punishapi.exceptions;

public class OffenseProcessingException extends Exception {
    public OffenseProcessingException() {
    }

    public OffenseProcessingException(String message) {
        super(message);
    }

    public OffenseProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public OffenseProcessingException(Throwable cause) {
        super(cause);
    }

    public OffenseProcessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
