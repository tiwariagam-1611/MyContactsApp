// UC-01: User Registration
// Goal: Allow new users to create an account with email, password, and profile info.
// Use: Provides entry point into the system; ensures only registered users can manage contacts.
// OOP: Encapsulation of user fields, validation logic, password hashing.
// Patterns: Factory (FreeUser, PremiumUser), Builder (User object construction).

// @author Developer
// @version 1.0

package com.seveneleven.mycontactsapp;

import java.util.Scanner;

import com.seveneleven.mycontactsapp.service.RegistrationService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegistrationService service = new RegistrationService();

        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter user type (FREE/PREMIUM):");
        String type = scanner.nextLine();

        try {
            service.registerUser(email, password, name, type);
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }
}

