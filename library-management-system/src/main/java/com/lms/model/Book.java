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

    public void addCopy(BookItem bookItem){
        copies.add(bookItem);
    }

    public BookItem getAvailableCopy(){
        return copies.stream().filter(book -> book.getStatus()==BookStatus.AVAILABLE).findFirst().orElse(null);
    }

    public long getAvailableCount(){
        return copies.stream().filter(book -> book.getStatus() == BookStatus.AVAILABLE).count();
    }

    public String getIsbn()   { return isbn; }
    public String getTitle()  { return title; }
    public String getAuthor() { return author; }
}
