package com.seveneleven.mycontactsapp.auth;


import com.seveneleven.mycontactsapp.model.User;

public class OAuth implements AuthenticationStrategy {
    @Override
    public boolean authenticate(String email, String password, User user) {
        // Demo: pretend OAuth always succeeds if email matches
        return user != null && user.getEmail().equalsIgnoreCase(email);
    }
}
