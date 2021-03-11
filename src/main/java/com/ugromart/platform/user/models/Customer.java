package com.ugromart.platform.user.models;

import lombok.Data;

@Data
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String PhoneContact;
    private String Address;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String phoneContact, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        PhoneContact = phoneContact;
        Address = address;
    }
}
