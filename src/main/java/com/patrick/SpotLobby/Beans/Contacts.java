package com.patrick.SpotLobby.Beans;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
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
    
    public Contacts() {}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (contactID ^ (contactID >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacts other = (Contacts) obj;
		if (contactID != other.contactID)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}

