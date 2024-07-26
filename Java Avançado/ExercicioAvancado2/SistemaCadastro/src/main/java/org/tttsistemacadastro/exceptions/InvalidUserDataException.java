package org.tttsistemacadastro.exceptions;

public class InvalidUserDataException extends IllegalArgumentException {
    public InvalidUserDataException(String message) {
        super(message);
    }
}
