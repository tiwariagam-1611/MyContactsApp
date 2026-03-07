package com.seveneleven.mycontactsapp.service;

import com.seveneleven.mycontactsapp.model.User;
import com.seveneleven.mycontactsapp.security.PasswordHasher;
import com.seveneleven.mycontactsapp.validation.ValidationUtil;

public class ProfileService {

    public void updateName(User user, String newName) {
        ValidationUtil.validateName(newName);
        user.setName(newName);
        System.out.println("Name updated to: " + newName);
    }

    public void updatePassword(User user, String newPassword) {
        ValidationUtil.validatePassword(newPassword);
        String hashed = PasswordHasher.hash(newPassword);
        user.setPasswordHash(hashed);
        System.out.println("Password updated.");
    }

    public void updateEmail(User user, String newEmail) {
        ValidationUtil.validateEmail(newEmail);
        user.setEmail(newEmail);
        System.out.println("Email updated to: " + newEmail);
    }
}
