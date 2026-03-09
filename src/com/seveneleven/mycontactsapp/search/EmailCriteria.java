package com.seveneleven.mycontactsapp.search;

import com.seveneleven.mycontactsapp.model.Contact;

public class EmailCriteria implements SearchCriteria {
    private final String emailPattern;

    public EmailCriteria(String emailPattern) {
        this.emailPattern = emailPattern.toLowerCase();
    }

    @Override
    public boolean matches(Contact contact) {
        return contact.getEmails().stream()
                .anyMatch(e -> e.toLowerCase().contains(emailPattern));
    }
}