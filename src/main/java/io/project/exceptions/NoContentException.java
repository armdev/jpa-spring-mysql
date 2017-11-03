package io.project.exceptions;

public class NoContentException extends RuntimeException {

    public NoContentException() {
        super();
    }

    public NoContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoContentException(String message) {
        super(message);
    }

    public NoContentException(Throwable cause) {
        super(cause);
    }
}
