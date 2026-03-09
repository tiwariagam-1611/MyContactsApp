// UC-04: Create Contact
// Goal: Allow logged-in users to add a new contact with name, phone numbers, email addresses, and optional fields.
// Use: Provides core functionality for building a personal or organizational contact list.
// Actor: Logged-in User
// OOP Concepts: Contact class hierarchy (Person, Organization), composition (Contact has PhoneNumber, Email objects).
// Design Patterns: Builder Pattern for constructing complex Contact objects, Factory Pattern for instantiating different contact types.
// Java Concepts: Collections (List for multiple phones/emails), LocalDateTime for timestamps, UUID for unique IDs.
// Security: Input validation for phone/email formats, safe handling of unique identifiers.

// @author Developer
// @version 4.0

package com.seveneleven.mycontactsapp;

import com.seveneleven.mycontactsapp.service.RegistrationService;
import com.seveneleven.mycontactsapp.service.AuthenticationService;
import com.seveneleven.mycontactsapp.service.ProfileService;
import com.seveneleven.mycontactsapp.service.ContactService;
import com.seveneleven.mycontactsapp.auth.BasicAuth;
import com.seveneleven.mycontactsapp.auth.OAuth;
import com.seveneleven.mycontactsapp.auth.SessionManager;
import com.seveneleven.mycontactsapp.model.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegistrationService regService = new RegistrationService();
        ProfileService profileService = new ProfileService();
        ContactService contactService = new ContactService();

        boolean running = true;

        while (running) {
            System.out.println("\n=== MyContactApp ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": // Registration
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

                case "2": // Login
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

                case "3": // Exit
                    running = false;
                    System.out.println("Exiting MyContactApp. Goodbye!");
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
            System.out.println("4. Logout");
            System.out.println("5. Exit");
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
                    new AuthenticationService(new BasicAuth()).logout(); // logout via SessionManager
                    loggedInMenu = false;
                    break;

                case "5":
                    System.out.println("Exiting MyContactApp. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
