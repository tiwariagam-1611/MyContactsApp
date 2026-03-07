package com.seveneleven.mycontactsapp.auth;


import com.seveneleven.mycontactsapp.model.User;

public class SessionManager {
    private static SessionManager instance;
    private User loggedInUser;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(User user) {
        this.loggedInUser = user;
        System.out.println("User logged in: " + user.getEmail());
    }

    public void logout() {
        System.out.println("User logged out: " + (loggedInUser != null ? loggedInUser.getEmail() : "none"));
        this.loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}

