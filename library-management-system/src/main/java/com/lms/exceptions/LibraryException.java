package com.lms.exceptions;

public class LibraryException extends RuntimeException {
    public LibraryException(String message) {
        super(message);
    }

    public static class BookNotAvailableException extends LibraryException {
        public BookNotAvailableException(String isbn) {
            super("No available copy for ISBN: " + isbn);
        }
    }

    public static class BorrowLimitExceededException extends LibraryException {
        public BorrowLimitExceededException(String memberId) {
            super("Borrow limit reached for member: " + memberId);
        }
    }

    public static class BookNotFoundException extends LibraryException {
        public BookNotFoundException(String detail) {
            super("Book not found: " + detail);
        }
    }
}
