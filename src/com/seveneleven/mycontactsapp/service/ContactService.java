package com.seveneleven.mycontactsapp.service;

import java.util.Optional;

import com.seveneleven.mycontactsapp.builder.ContactBuilder;
import com.seveneleven.mycontactsapp.command.CommandManager;
import com.seveneleven.mycontactsapp.command.EditContactCommand;
import com.seveneleven.mycontactsapp.decorator.BasicContactDisplay;
import com.seveneleven.mycontactsapp.decorator.ContactDisplay;
import com.seveneleven.mycontactsapp.decorator.PrettyContactDisplay;
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
    public void viewContact(String name) {
        Optional<Contact> contactOpt = ContactRepository.getAllContacts().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();

        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            ContactDisplay display = new PrettyContactDisplay(new BasicContactDisplay());
            System.out.println(display.format(contact));
        } else {
            System.out.println("Contact not found: " + name);
        }
    }
    public void editContact(String name, String newName, String newPhone, String newEmail) {
        Optional<Contact> contactOpt = ContactRepository.getAllContacts().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();

        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            CommandManager manager = new CommandManager();
            EditContactCommand cmd = new EditContactCommand(contact, newName, newPhone, newEmail);
            manager.executeCommand(cmd);
            // manager.undoLast(); // if user wants undo
        } else {
            System.out.println("Contact not found: " + name);
        }
    }

}

