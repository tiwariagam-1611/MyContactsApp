package com.seveneleven.mycontactsapp.filter;

import com.seveneleven.mycontactsapp.model.Contact;

public class TagFilter implements Filter {
    private final String tag;

    public TagFilter(String tag) {
        this.tag = tag.toLowerCase();
    }

    @Override
    public boolean apply(Contact contact) {
        return contact.getTags().stream()
                .anyMatch(t -> t.toLowerCase().contains(tag));
    }
}

