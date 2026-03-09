package com.seveneleven.mycontactsapp.composite;

import com.seveneleven.mycontactsapp.model.Contact;

public interface ContactComponent {
    void delete();
    void tag(String label);
    void export();
    Contact getContact();
}
