package com.seveneleven.mycontactsapp.search;

import com.seveneleven.mycontactsapp.model.Contact;
import com.seveneleven.mycontactsapp.model.Tag;

public class TagCriteria implements SearchCriteria {
    private final String tag;

    public TagCriteria(String tag) {
        this.tag = tag.toLowerCase();
    }

    @Override
    public boolean matches(Contact contact) {
        return contact.getTags().stream()
                .map(Tag::getName)
                .anyMatch(t -> t.toLowerCase().contains(tag));
    }
}
