package com.seveneleven.mycontactsapp.service;

import com.seveneleven.mycontactsapp.builder.ContactBuilder;
import com.seveneleven.mycontactsapp.model.Contact;
import com.seveneleven.mycontactsapp.repo.ContactRepository;

public class ContactService {
    public void createContact(String type, String name, String phone, String email) {
        Contact contact = new ContactBuilder()
                .setType(type)
                .setName(name)
                .addPhone(phone)
                .addEmail(email)
                .build();

        ContactRepository.save(contact);
        System.out.println("Created contact: " + contact);
    }

    public void listContacts() {
        System.out.println("\n--- All Contacts ---");
        ContactRepository.getAllContacts().forEach(System.out::println);
    }
}

