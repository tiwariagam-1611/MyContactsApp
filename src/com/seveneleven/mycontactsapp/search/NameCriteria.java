package com.seveneleven.mycontactsapp.search;

import com.seveneleven.mycontactsapp.model.Contact;

public class NameCriteria implements SearchCriteria {
    private final String name;

    public NameCriteria(String name) {
        this.name = name.toLowerCase();
    }

    @Override
    public boolean matches(Contact contact) {
        return contact.getName().toLowerCase().contains(name);
    }
}