package com.seveneleven.mycontactsapp.decorator;

import com.seveneleven.mycontactsapp.model.Contact;

public class PrettyContactDisplay implements ContactDisplay {
    private final ContactDisplay inner;

    public PrettyContactDisplay(ContactDisplay inner) {
        this.inner = inner;
    }

    @Override
    public String format(Contact contact) {
        return "\n--- Contact Details ---\n"
             + "Name: " + contact.getName() + "\n"
             + "Phones: " + contact.getPhones() + "\n"
             + "Emails: " + contact.getEmails() + "\n"
             + "-----------------------\n";
    }
}
