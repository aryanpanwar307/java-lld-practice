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

}
