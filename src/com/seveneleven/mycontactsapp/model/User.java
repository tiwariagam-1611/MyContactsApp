package com.seveneleven.mycontactsapp.model;

public abstract class User {
    private final String email;
    private final String passwordHash;
    private final String name;

    protected User(String email, String passwordHash, String name) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPasswordHash() { return passwordHash; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + name + " (" + email + ")";
    }
}
