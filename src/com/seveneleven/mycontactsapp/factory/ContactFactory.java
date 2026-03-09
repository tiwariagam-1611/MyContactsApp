package com.seveneleven.mycontactsapp.factory;

import com.seveneleven.mycontactsapp.model.*;

public class ContactFactory {
    public static Contact createContact(String type, String name) {
        if ("PERSON".equalsIgnoreCase(type)) {
            return new PersonContact(name);
        } else if ("ORG".equalsIgnoreCase(type) || "ORGANIZATION".equalsIgnoreCase(type)) {
            return new OrganizationContact(name);
        }
        throw new IllegalArgumentException("Unknown contact type: " + type);
    }
}

