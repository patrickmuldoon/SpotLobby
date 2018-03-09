package com.patrick.SpotLobby.Beans;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CONTACTS")
public class Contacts {

    @Id
    @Column(nullable=false, name="CONTACT_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long contactID;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="Friend")
    @JsonIgnore
    private Users user;

    public Contacts(long contactID, Users user) {
        super();
        this.contactID = contactID;
        this.user = user;
    }

    public long getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contacts [contactID=" + contactID + ", user=" + (user.getFirstName() + user.getLastName())  + "]";
    }



}

