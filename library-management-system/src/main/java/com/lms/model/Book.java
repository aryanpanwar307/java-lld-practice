package com.lms.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final List<BookItem> copies;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.copies = new ArrayList<>();
    }

}
