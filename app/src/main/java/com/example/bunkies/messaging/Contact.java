package com.example.bunkies.messaging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contact {
    private String mName;
    private boolean mOnline;

    public Contact(String name, boolean online) {
        mName = name;
        mOnline = online;
    }

    public String getName() {
        return mName;
    }

    public boolean isOnline() {
        return mOnline;
    }

    private static int lastContactId = 0;

    public static ArrayList<Contact> createContactsList(int numContacts) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        List<String> names = Arrays.asList("Chad","Nick","Sam","Jessica","The Boyz");
        for (int i = 0; i < numContacts; i++) {
            contacts.add(new Contact(names.get(i), i <= numContacts / 2));
        }

        return contacts;
    }
}