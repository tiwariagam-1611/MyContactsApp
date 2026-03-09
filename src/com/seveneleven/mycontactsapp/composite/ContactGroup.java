package com.seveneleven.mycontactsapp.composite;

import com.seveneleven.mycontactsapp.model.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactGroup implements ContactComponent {
    private final List<ContactComponent> children = new ArrayList<>();

    public void add(ContactComponent component) {
        children.add(component);
    }

    @Override
    public void delete() {
        children.forEach(ContactComponent::delete);
    }

    @Override
    public void tag(String label) {
        children.forEach(c -> c.tag(label));
    }

    @Override
    public void export() {
        children.forEach(ContactComponent::export);
    }

    @Override
    public Contact getContact() {
        return null; // group doesn’t represent a single contact
    }
}
