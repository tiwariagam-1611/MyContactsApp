package com.seveneleven.mycontactsapp.filter;

import com.seveneleven.mycontactsapp.model.Contact;

public class FrequencyFilter implements Filter {
    private final int minCount;

    public FrequencyFilter(int minCount) {
        this.minCount = minCount;
    }

    @Override
    public boolean apply(Contact contact) {
        return contact.getContactCount() >= minCount;
    }
}
