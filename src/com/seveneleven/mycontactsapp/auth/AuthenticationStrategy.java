package com.seveneleven.mycontactsapp.auth;


import com.seveneleven.mycontactsapp.model.User;

public interface AuthenticationStrategy {
    boolean authenticate(String email, String password, User user);
}

