// UC-02: User Authentication
// Goal: Verify credentials and grant access to contact list.
// Use: Ensures secure login and session management.
// OOP: Authentication interface with polymorphic implementations (BasicAuth, OAuth).
// Patterns: Strategy (auth methods), Singleton (SessionManager).

// @author Developer
// @version 2.0
package com.seveneleven.mycontactsapp;

import com.seveneleven.mycontactsapp.service.RegistrationService;
import com.seveneleven.mycontactsapp.service.AuthenticationService;
import com.seveneleven.mycontactsapp.auth.BasicAuth;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegistrationService regService = new RegistrationService();

        // Registration
        System.out.println("=== Registration ===");
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
            return;
        }

        // Authentication
        System.out.println("\n=== Login ===");
        System.out.print("Enter email: ");
        String loginEmail = scanner.nextLine();
        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        AuthenticationService authService = new AuthenticationService(new BasicAuth());
        boolean loggedIn = authService.login(loginEmail, loginPassword);

        if (loggedIn) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed.");
        }

        authService.logout();
    }
}
