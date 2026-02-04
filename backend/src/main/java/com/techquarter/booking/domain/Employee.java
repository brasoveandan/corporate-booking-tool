package com.techquarter.booking.domain;

import java.util.Objects;

public class Employee {
    private final String id;
    private final String name;
    private final String email;

    public Employee(String id, String name, String email) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
