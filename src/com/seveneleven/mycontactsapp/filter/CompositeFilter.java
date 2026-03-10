package com.seveneleven.mycontactsapp.filter;

import com.seveneleven.mycontactsapp.model.Contact;
import java.util.List;

public class CompositeFilter implements Filter {
    private final List<Filter> filters;

    public CompositeFilter(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean apply(Contact contact) {
        return filters.stream().allMatch(f -> f.apply(contact));
    }
}
