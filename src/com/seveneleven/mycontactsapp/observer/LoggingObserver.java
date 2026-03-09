package com.seveneleven.mycontactsapp.observer;

import com.seveneleven.mycontactsapp.model.Contact;

public class LoggingObserver implements ContactDeletionObserver {
    @Override
    public void onContactDeleted(Contact contact) {
        System.out.println("[Observer] Contact deleted: " + contact.getName());
    }
}
