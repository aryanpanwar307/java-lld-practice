package com.lms.exceptions;

public class LibraryException extends RuntimeException {
    public LibraryException(String message) {
        super(message);
    }

    public static class BookNotFoundException extends LibraryException {
        public BookNotFoundException(String isbn) {
            super("Book with ISBN " + isbn + " not found.");
        }
    }
}
