package com.seveneleven.mycontactsapp.service;

import com.seveneleven.mycontactsapp.builder.UserBuilder;
import com.seveneleven.mycontactsapp.model.User;
import com.seveneleven.mycontactsapp.repo.UserRepository;

public class RegistrationService {
    public void registerUser(String email, String password, String name, String type) {
        User user = new UserBuilder()
                .setEmail(email)
                .setPassword(password)
                .setName(name)
                .build(type);

        UserRepository.save(user);
        System.out.println("Registration successful: " + user);
    }
}
