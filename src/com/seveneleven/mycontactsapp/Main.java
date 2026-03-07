// UC-03: User Profile Management
// Goal: Allow logged-in users to update profile details, change passwords, and manage preferences.
// Use: Provides personalization and account maintenance features.
// OOP: Encapsulation with setter methods, validation logic inside methods.
// Patterns: Command Pattern for profile update operations.
// Java Concepts: JavaBeans conventions, secure password handling, input validation.

// @author Developer
// @version 3.0

package com.seveneleven.mycontactsapp;

import com.seveneleven.mycontactsapp.service.RegistrationService;
import com.seveneleven.mycontactsapp.service.AuthenticationService;
import com.seveneleven.mycontactsapp.service.ProfileService;
import com.seveneleven.mycontactsapp.auth.BasicAuth;
import com.seveneleven.mycontactsapp.auth.SessionManager;
import com.seveneleven.mycontactsapp.model.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegistrationService regService = new RegistrationService();
        AuthenticationService authService = new AuthenticationService(new BasicAuth());
        ProfileService profileService = new ProfileService();

        boolean running = true;

        while (running) {
            System.out.println("\n=== MyContacts Menu ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Update Profile");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
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
                    System.out.print("Enter email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    boolean loggedIn = authService.login(loginEmail, loginPassword);
                    if (loggedIn) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Login failed.");
                    }
                    break;

                case "3": // Profile Update
                    User loggedInUser = SessionManager.getInstance().getLoggedInUser();
                    if (loggedInUser == null) {
                        System.out.println("No user logged in. Please login first.");
                        break;
                    }

                    System.out.println("\n--- Profile Update ---");
                    System.out.print("Enter new name (leave blank to skip): ");
                    String newName = scanner.nextLine();
                    if (!newName.isBlank()) {
                        profileService.updateName(loggedInUser, newName);
                    }

                    System.out.print("Enter new password (leave blank to skip): ");
                    String newPass = scanner.nextLine();
                    if (!newPass.isBlank()) {
                        profileService.updatePassword(loggedInUser, newPass);
                    }

                    System.out.print("Enter new email (leave blank to skip): ");
                    String newEmail = scanner.nextLine();
                    if (!newEmail.isBlank()) {
                        profileService.updateEmail(loggedInUser, newEmail);
                    }

                    System.out.println("Updated profile: " + loggedInUser);
                    break;

                case "4": // Logout
                    authService.logout();
                    break;

                case "5": // Exit
                    running = false;
                    System.out.println("Exiting MyContacts. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
