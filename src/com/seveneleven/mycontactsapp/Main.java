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

