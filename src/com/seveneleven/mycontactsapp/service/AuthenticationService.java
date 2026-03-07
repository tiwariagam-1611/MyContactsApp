package com.seveneleven.mycontactsapp.service;

import com.seveneleven.mycontactsapp.auth.AuthenticationStrategy;
import com.seveneleven.mycontactsapp.auth.SessionManager;
import com.seveneleven.mycontactsapp.model.User;
import com.seveneleven.mycontactsapp.repo.UserRepository;

public class AuthenticationService {
    private final AuthenticationStrategy strategy;

    public AuthenticationService(AuthenticationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean login(String email, String password) {
        // Find user in repository
        User user = UserRepository.getAllUsers().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);

        boolean success = strategy.authenticate(email, password, user);
        if (success) {
            SessionManager.getInstance().login(user);
        } else {
            System.out.println("Login failed for " + email);
        }
        return success;
    }

    public void logout() {
        SessionManager.getInstance().logout();
    }
}
