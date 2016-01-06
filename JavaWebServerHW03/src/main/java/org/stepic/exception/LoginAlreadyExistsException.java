package org.stepic.exception;

public class LoginAlreadyExistsException extends Exception {

    public LoginAlreadyExistsException() {
    }

    public LoginAlreadyExistsException(String message) {
        super(message);
    }

    public LoginAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
