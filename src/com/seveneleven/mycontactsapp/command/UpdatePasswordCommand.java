package com.seveneleven.mycontactsapp.command;


import com.seveneleven.mycontactsapp.model.User;
import com.seveneleven.mycontactsapp.security.PasswordHasher;

public class UpdatePasswordCommand implements Command {
    private final User user;
    private final String newPassword;

    public UpdatePasswordCommand(User user, String newPassword) {
        this.user = user;
        this.newPassword = newPassword;
    }

    @Override
    public void execute() {
        String hashed = PasswordHasher.hash(newPassword);
        user.setPasswordHash(hashed);
        System.out.println("Password updated.");
    }

}
