package com.seveneleven.mycontactsapp.builder;

import com.seveneleven.mycontactsapp.factory.UserFactory;
import com.seveneleven.mycontactsapp.model.User;
import com.seveneleven.mycontactsapp.security.PasswordHasher;
import com.seveneleven.mycontactsapp.validation.ValidationUtil;

public class UserBuilder {
    private String email;
    private String password;
    private String name;

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public User build(String type) {
        ValidationUtil.validateEmail(email);
        ValidationUtil.validatePassword(password);
        ValidationUtil.validateName(name);

        String hashed = PasswordHasher.hash(password);
        return UserFactory.createUser(type, email, hashed, name);
    }
}
