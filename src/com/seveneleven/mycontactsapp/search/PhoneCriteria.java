package com.seveneleven.mycontactsapp.search;

import com.seveneleven.mycontactsapp.model.Contact;

public class PhoneCriteria implements SearchCriteria {
    private final String phonePattern;

    public PhoneCriteria(String phonePattern) {
        this.phonePattern = phonePattern;
    }

    @Override
    public boolean matches(Contact contact) {
        return contact.getPhones().stream()
                .anyMatch(p -> p.contains(phonePattern));
    }
}
