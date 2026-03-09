package com.seveneleven.mycontactsapp.decorator;

import com.seveneleven.mycontactsapp.model.Contact;

public class BasicContactDisplay implements ContactDisplay {
    @Override
    public String format(Contact contact) {
        return contact.toString(); // uses Contact’s toString()
    }
}
