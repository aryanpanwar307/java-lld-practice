package com.lms.model;

public class Librarian extends Person {
    private final String empId;

    public Librarian(String empId, String name, String email) {
        super(name, email);
        this.empId = empId;
    }

    @Override
    public String getRole() {
        return "Librarian";
    }

    public void addBookToCatalog(Catalog catalog, Book book) {
        catalog.addBook(book);
        System.out.println(getName() + " added: " + book.getTitle());
    }

    public void addBookItem(Book book, BookItem item) {
        book.addCopy(item);
    }

    public String getEmpId() {
        return empId;
    }

}
