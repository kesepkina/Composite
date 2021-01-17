package com.epam.composite.exception;

public class ReaderException extends Exception {
    public ReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReaderException(Throwable cause) {
        super(cause);
    }
}
