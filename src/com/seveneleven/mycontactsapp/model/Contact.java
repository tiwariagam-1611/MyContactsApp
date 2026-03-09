package com.seveneleven.mycontactsapp.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Contact {
    private String name;
    private List<String> phones = new ArrayList<>();
    private List<String> emails = new ArrayList<>();

    protected Contact(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public List<String> getPhones() { return phones; }
    public List<String> getEmails() { return emails; }

    public void addPhone(String phone) { phones.add(phone); }
    public void addEmail(String email) { emails.add(email); }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + name +
               " Phones=" + phones +
               " Emails=" + emails;
    }
}

