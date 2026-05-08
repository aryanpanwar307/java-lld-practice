package com.lms.model;


public class BookItem {
    private final String barcode;
    private BookStatus status;
    private Book book;

    public BookItem(String barcode, Book book){
        this.barcode = barcode;
        this.book = book;
        this.status = BookStatus.AVAILABLE;
    }

    public void checkout(){
        this.status = BookStatus.BORROWED;
    }
    public void returnBook(){
        this.status = BookStatus.AVAILABLE;
    }
    public boolean isAvialable(){
        return status == BookStatus.AVAILABLE;
    }

    public String getBarcode(){
        return barcode;
    }

    public Book getBook(){
        return book;
    }

    public BookStatus getStatus(){ return status; }
    
}
