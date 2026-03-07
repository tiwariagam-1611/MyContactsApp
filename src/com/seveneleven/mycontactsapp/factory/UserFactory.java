package com.seveneleven.mycontactsapp.factory;

import com.seveneleven.mycontactsapp.model.FreeUser;
import com.seveneleven.mycontactsapp.model.PremiumUser;
import com.seveneleven.mycontactsapp.model.User;

public class UserFactory {
    public static User createUser(String type, String email, String passwordHash, String name) {
        if ("FREE".equalsIgnoreCase(type)) {
            return new FreeUser(email, passwordHash, name);
        } else if ("PREMIUM".equalsIgnoreCase(type)) {
            return new PremiumUser(email, passwordHash, name);
        }
        throw new IllegalArgumentException("Unknown user type: " + type);
    }
}

