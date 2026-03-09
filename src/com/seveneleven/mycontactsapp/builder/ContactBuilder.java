package com.seveneleven.mycontactsapp.builder;

import com.seveneleven.mycontactsapp.model.Contact;
import com.seveneleven.mycontactsapp.factory.ContactFactory;

import java.util.ArrayList;
import java.util.List;

public class ContactBuilder {
    private String type;
    private String name;
    private List<String> phones = new ArrayList<>();
    private List<String> emails = new ArrayList<>();

    public ContactBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public ContactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ContactBuilder addPhone(String phone) {
        phones.add(phone);
        return this;
    }

    public ContactBuilder addEmail(String email) {
        emails.add(email);
        return this;
    }

    public Contact build() {
        Contact contact = ContactFactory.createContact(type, name);
        phones.forEach(contact::addPhone);
        emails.forEach(contact::addEmail);
        return contact;
    }
}

