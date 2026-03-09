package com.seveneleven.mycontactsapp.search;


import com.seveneleven.mycontactsapp.model.Contact;

public interface SearchCriteria {
    boolean matches(Contact contact);
}











