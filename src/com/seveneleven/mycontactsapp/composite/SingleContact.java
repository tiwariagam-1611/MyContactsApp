package com.seveneleven.mycontactsapp.composite;

import com.seveneleven.mycontactsapp.model.Contact;
import com.seveneleven.mycontactsapp.repo.ContactRepository;

public class SingleContact implements ContactComponent {
    private final Contact contact;

    public SingleContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public void delete() {
        ContactRepository.delete(contact);
        System.out.println("Deleted: " + contact.getName());
    }

    @Override
    public void tag(String label) {
        System.out.println("Tagged " + contact.getName() + " with label: " + label);
    }

    @Override
    public void export() {
        System.out.println("Exporting contact: " + contact);
    }

    @Override
    public Contact getContact() {
        return contact;
    }
}

