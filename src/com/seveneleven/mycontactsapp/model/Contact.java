package com.seveneleven.mycontactsapp.model;

import java.time.LocalDate;
import java.util.*;

public abstract class Contact {
    private final String uuid;              // unique identifier
    private String name;
    private List<String> phones = new ArrayList<>();
    private List<String> emails = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private final LocalDate dateAdded;      // when contact was created
    private int contactCount;               // how often this contact is used

    protected Contact(String name) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.dateAdded = LocalDate.now();
        this.contactCount = 0;
    }

    // Getters
    public String getUuid() { return uuid; }
    public String getName() { return name; }
    public List<String> getPhones() { return phones; }
    public List<String> getEmails() { return emails; }
    public List<String> getTags() { return tags; }
    public LocalDate getDateAdded() { return dateAdded; }
    public int getContactCount() { return contactCount; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPhones(List<String> phones) { this.phones = new ArrayList<>(phones); }
    public void setEmails(List<String> emails) { this.emails = new ArrayList<>(emails); }
    public void setTags(List<String> tags) { this.tags = new ArrayList<>(tags); }

    // Adders
    public void addPhone(String phone) { phones.add(phone); }
    public void addEmail(String email) { emails.add(email); }
    public void addTag(String tag) { tags.add(tag); }

    // Usage tracking
    public void incrementContactCount() { contactCount++; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + name +
               " Phones=" + phones +
               " Emails=" + emails +
               " Tags=" + tags +
               " DateAdded=" + dateAdded +
               " UUID=" + uuid +
               " ContactCount=" + contactCount;
    }
}
