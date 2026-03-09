package com.seveneleven.mycontactsapp.memento;

import com.seveneleven.mycontactsapp.model.Contact;
import java.util.ArrayList;

public class ContactMemento {
    private final String name;
    private final java.util.List<String> phones;
    private final java.util.List<String> emails;

    public ContactMemento(Contact contact) {
        this.name = contact.getName();
        this.phones = new ArrayList<>(contact.getPhones()); // defensive copy
        this.emails = new ArrayList<>(contact.getEmails());
    }

    public String getName() { return name; }
    public java.util.List<String> getPhones() { return phones; }
    public java.util.List<String> getEmails() { return emails; }
}

