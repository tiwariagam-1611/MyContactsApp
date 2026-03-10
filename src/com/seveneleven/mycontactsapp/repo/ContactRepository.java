package com.seveneleven.mycontactsapp.repo;

import com.seveneleven.mycontactsapp.model.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
	private static final List<Contact> contacts = new ArrayList<>();

	public static void save(Contact contact) {
		contacts.add(contact);
		System.out.println("Contact saved.");
	}

	public static List<Contact> getAllContacts() {
		return contacts;
	}

	public static boolean delete(Contact contact) {
		return contacts.remove(contact); // hard delete
	}
	public static Contact findByName(String name) {
		return contacts.stream()
				.filter(c -> c.getName().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
	}

}
