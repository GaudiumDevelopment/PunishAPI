package me.superbiebel.punishapi.exceptions;

public class FailedDataOperationException extends Exception {
    public FailedDataOperationException() {
        super();
    }

    public FailedDataOperationException(String message) {
        super(message);
    }

    public FailedDataOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedDataOperationException(Throwable cause) {
        super(cause);
    }

    public FailedDataOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
