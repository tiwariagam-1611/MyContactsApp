package com.seveneleven.mycontactsapp.observer;

import com.seveneleven.mycontactsapp.model.Contact;

public interface ContactDeletionObserver {
    void onContactDeleted(Contact contact);
}
