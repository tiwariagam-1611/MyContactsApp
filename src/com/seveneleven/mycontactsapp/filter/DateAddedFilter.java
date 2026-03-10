package com.seveneleven.mycontactsapp.filter;

import com.seveneleven.mycontactsapp.model.Contact;
import java.time.LocalDate;

public class DateAddedFilter implements Filter {
    private final LocalDate after;

    public DateAddedFilter(LocalDate after) {
        this.after = after;
    }

    @Override
    public boolean apply(Contact contact) {
        return contact.getDateAdded().isAfter(after);
    }
}
