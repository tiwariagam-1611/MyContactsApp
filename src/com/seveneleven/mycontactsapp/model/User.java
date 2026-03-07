package com.seveneleven.mycontactsapp.model;

public abstract class User {
    private String email;
    private String passwordHash;
    private String name;

    protected User(String email, String passwordHash, String name) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPasswordHash() { return passwordHash; }

    // Setters for profile updates
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + name + " (" + email + ")";
    }
}
