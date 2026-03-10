package com.seveneleven.mycontactsapp.filter;

import com.seveneleven.mycontactsapp.model.Contact;

public interface Filter {
    boolean apply(Contact contact);
}
