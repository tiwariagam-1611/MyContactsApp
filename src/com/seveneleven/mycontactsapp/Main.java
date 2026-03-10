// UC-10: Advanced Filtering
// Goal: Allow logged-in users to apply multiple filters (by tag, date added, frequently contacted) 
//       to refine contact searches beyond basic criteria.
// Use: Provides granular control over contact retrieval, enabling users to quickly narrow down 
//      results based on personalized preferences.
// Actor: Logged-in User
// OOP Concepts: Filter interface hierarchy, composite filters for combining multiple conditions.
// Design Patterns: Composite Pattern for building complex filter sets, Strategy Pattern for 
//                  interchangeable filter algorithms.
// Java Concepts: Comparator for sorting results, multi-level filtering with Stream API, 
//                functional interfaces for dynamic filter application.
// Security: Ensures filters respect user privacy, preventing unauthorized access to hidden or 
//           restricted contact attributes.
//
// @author Developer
// @version 10.0

package com.seveneleven.mycontactsapp;

import com.seveneleven.mycontactsapp.service.RegistrationService;
import com.seveneleven.mycontactsapp.service.AuthenticationService;
import com.seveneleven.mycontactsapp.service.ProfileService;
import com.seveneleven.mycontactsapp.service.ContactService;
import com.seveneleven.mycontactsapp.auth.BasicAuth;
import com.seveneleven.mycontactsapp.auth.OAuth;
import com.seveneleven.mycontactsapp.auth.SessionManager;
import com.seveneleven.mycontactsapp.model.User;
import com.seveneleven.mycontactsapp.search.*;
import com.seveneleven.mycontactsapp.filter.*;

import java.util.*;
import java.time.LocalDate;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		RegistrationService regService = new RegistrationService();
		ProfileService profileService = new ProfileService();
		ContactService contactService = new ContactService();

		boolean running = true;

		while (running) {
			System.out.println("\n=== MyContactsApp ===");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");
			String choice = scanner.nextLine();

			switch (choice) {
			case "1":
				System.out.println("\n--- Registration ---");
				System.out.print("Enter email: ");
				String email = scanner.nextLine();
				System.out.print("Enter password: ");
				String password = scanner.nextLine();
				System.out.print("Enter name: ");
				String name = scanner.nextLine();
				System.out.print("Enter user type (FREE/PREMIUM): ");
				String type = scanner.nextLine();

				try {
					regService.registerUser(email, password, name, type);
				} catch (IllegalArgumentException e) {
					System.out.println("Registration failed: " + e.getMessage());
				}
				break;

			case "2":
				System.out.println("\n--- Login ---");
				System.out.println("1. BasicAuth (email + password)");
				System.out.println("2. OAuth (email only)");
				System.out.print("Choose login method: ");
				String loginChoice = scanner.nextLine();

				boolean loggedIn = false;
				if ("1".equals(loginChoice)) {
					System.out.print("Enter email: ");
					String loginEmail = scanner.nextLine();
					System.out.print("Enter password: ");
					String loginPassword = scanner.nextLine();
					AuthenticationService authService = new AuthenticationService(new BasicAuth());
					loggedIn = authService.login(loginEmail, loginPassword);
				} else if ("2".equals(loginChoice)) {
					System.out.print("Enter email: ");
					String loginEmail = scanner.nextLine();
					AuthenticationService authService = new AuthenticationService(new OAuth());
					loggedIn = authService.login(loginEmail, "");
				}

				if (loggedIn) {
					System.out.println("Login successful!");
					postLoginMenu(scanner, profileService, contactService);
				} else {
					System.out.println("Login failed.");
				}
				break;

			case "3":
				running = false;
				System.out.println("Exiting MyContactsApp. Goodbye!");
				break;

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

		scanner.close();
	}

	private static void postLoginMenu(Scanner scanner, ProfileService profileService, ContactService contactService) {
		boolean loggedInMenu = true;
		while (loggedInMenu) {
			User loggedInUser = SessionManager.getInstance().getLoggedInUser();
			System.out.println("\n=== Logged-In Menu ===");
			System.out.println("1. Update Profile");
			System.out.println("2. Create Contact");
			System.out.println("3. List Contacts");
			System.out.println("4. View Contact Details");
			System.out.println("5. Edit Contact");
			System.out.println("6. Delete Contact");
			System.out.println("7. Bulk Operations");
			System.out.println("8. Search Contacts");
			System.out.println("9. Advanced Filtering");
			System.out.println("10. Logout");
			System.out.println("11. Exit");
			System.out.print("Choose an option: ");
			String choice = scanner.nextLine();

			switch (choice) {
			case "1":
				System.out.println("\n--- Profile Update ---");
				System.out.print("Enter new name (leave blank to skip): ");
				String newName = scanner.nextLine();
				if (!newName.isBlank()) profileService.updateName(loggedInUser, newName);

				System.out.print("Enter new password (leave blank to skip): ");
				String newPass = scanner.nextLine();
				if (!newPass.isBlank()) profileService.updatePassword(loggedInUser, newPass);

				System.out.print("Enter new email (leave blank to skip): ");
				String newEmail = scanner.nextLine();
				if (!newEmail.isBlank()) profileService.updateEmail(loggedInUser, newEmail);

				System.out.println("Updated profile: " + loggedInUser);
				break;

			case "2":
				System.out.println("\n--- Create Contact ---");
				System.out.print("Enter contact type (PERSON/ORG): ");
				String cType = scanner.nextLine();
				System.out.print("Enter name: ");
				String cName = scanner.nextLine();
				System.out.print("Enter phone: ");
				String cPhone = scanner.nextLine();
				System.out.print("Enter email: ");
				String cEmail = scanner.nextLine();

				try {
					contactService.createContact(cType, cName, cPhone, cEmail);
				} catch (IllegalArgumentException e) {
					System.out.println("Failed to create contact: " + e.getMessage());
				}
				break;

			case "3":
				contactService.listContacts();
				break;

			case "4":
				System.out.print("Enter contact name to view: ");
				String searchName = scanner.nextLine();
				contactService.viewContact(searchName);
				break;

			case "5":
				System.out.print("Enter contact name to edit: ");
				String editName = scanner.nextLine();
				System.out.print("Enter new name (leave blank to skip): ");
				String editNewName = scanner.nextLine();
				System.out.print("Enter new phone (leave blank to skip): ");
				String editNewPhone = scanner.nextLine();
				System.out.print("Enter new email (leave blank to skip): ");
				String editNewEmail = scanner.nextLine();
				contactService.editContact(editName, editNewName, editNewPhone, editNewEmail);
				break;

			case "6":
				System.out.print("Enter contact name to delete: ");
				String delName = scanner.nextLine();
				contactService.deleteContact(delName);
				break;

			case "7":
				System.out.println("\n--- Bulk Operations ---");
				System.out.print("Enter contact names separated by commas: ");
				String bulkNames = scanner.nextLine();
				List<String> names = Arrays.stream(bulkNames.split(","))
						.map(String::trim)
						.toList();

				System.out.println("Choose bulk action: 1=Delete, 2=Tag, 3=Export");
				String action = scanner.nextLine();

				switch (action) {
				case "1":
					contactService.bulkDelete(names);
					break;
				case "2":
					System.out.print("Enter tag label: ");
					String label = scanner.nextLine();
					contactService.bulkTag(names, label);
					break;
				case "3":
					contactService.bulkExport(names);
					break;
				default:
					System.out.println("Invalid bulk action.");
				}
				break;

			case "8":
				System.out.println("\n--- Search Contacts ---");
				System.out.println("Search by: 1=Name, 2=Phone, 3=Email, 4=Tag");
				String searchChoice = scanner.nextLine();
				System.out.print("Enter search value: ");
				String value = scanner.nextLine();

				SearchCriteria criteria = null;
				switch (searchChoice) {
				case "1": criteria = new NameCriteria(value); break;
				case "2": criteria = new PhoneCriteria(value); break;
				case "3": criteria = new EmailCriteria(value); break;
				case "4": criteria = new TagCriteria(value); break;
				default: System.out.println("Invalid search option."); break;
				}

				if (criteria != null) {
					contactService.searchContacts(criteria);
				}
				break;

			case "9":
				System.out.println("\n--- Advanced Filtering ---");
				System.out.println("Filter by: 1=Tag, 2=Date Added, 3=Frequency");
				String filterChoice = scanner.nextLine();
				Filter filter = null;

				switch (filterChoice) {
				case "1":
					System.out.print("Enter tag: ");
					filter = new TagFilter(scanner.nextLine());
					break;
				case "2":
					System.out.print("Enter date (YYYY-MM-DD): ");
					filter = new DateAddedFilter(LocalDate.parse(scanner.nextLine()));
					break;
				case "3":
					System.out.print("Enter minimum contact count: ");
					filter = new FrequencyFilter(Integer.parseInt(scanner.nextLine()));
					break;
				default:
					System.out.println("Invalid filter option.");
					break;
				}

				if (filter != null) {
					System.out.println("Sort by: 1=Name, 2=Date Added, 3=Frequency");
					String sortChoice = scanner.nextLine();
					Comparator<com.seveneleven.mycontactsapp.model.Contact> sorter = Comparator.comparing(com.seveneleven.mycontactsapp.model.Contact::getName);

					if ("2".equals(sortChoice)) {
						sorter = Comparator.comparing(com.seveneleven.mycontactsapp.model.Contact::getDateAdded);
					} else if ("3".equals(sortChoice)) {
						sorter = Comparator.comparing(com.seveneleven.mycontactsapp.model.Contact::getContactCount).reversed();
					}

					contactService.advancedFilter(filter, sorter);
				}
				break;

			case "10":
				new AuthenticationService(new BasicAuth()).logout();
				loggedInMenu = false;
				break;

			case "11":
				System.out.println("Exiting MyContactsApp. Goodbye!");
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}

