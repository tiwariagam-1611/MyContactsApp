package com.seveneleven.mycontactsapp.search;

import java.util.List;

import com.seveneleven.mycontactsapp.model.Contact;

public class CompositeCriteria implements SearchCriteria {
    private final List<SearchCriteria> criteriaList;

    public CompositeCriteria(List<SearchCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    @Override
    public boolean matches(Contact contact) {
        return criteriaList.stream().allMatch(c -> c.matches(contact));
    }
}

