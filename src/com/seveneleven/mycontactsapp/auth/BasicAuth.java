package com.seveneleven.mycontactsapp.auth;


import com.seveneleven.mycontactsapp.model.User;
import com.seveneleven.mycontactsapp.security.PasswordHasher;

public class BasicAuth implements AuthenticationStrategy {
    @Override
    public boolean authenticate(String email, String password, User user) {
        if (user == null) return false;
        if (!user.getEmail().equalsIgnoreCase(email)) return false;
        return PasswordHasher.matches(password, user.getPasswordHash());
    }
}

