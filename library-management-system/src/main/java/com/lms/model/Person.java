package com.lms.model;

public abstract class Person {
    private final String name;
    private final String email;

    public Person(String name, String email) {
        this.email = email;
        this.name = name;
    }

    public abstract String getRole();

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
