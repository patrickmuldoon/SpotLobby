package com.patrick.SpotLobby.Beans;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Friend implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="FRIEND_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private long friendID;
	
	@ManyToMany(mappedBy="friends", cascade=CascadeType.ALL)
	private Set<Users> users;

	public Friend(long friendID, Set<Users> users) {
		super();
		this.friendID = friendID;
		this.users = users;
	}

	public long getFriendID() {
		return friendID;
	}

	public void setFriendID(long friendID) {
		this.friendID = friendID;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (friendID ^ (friendID >>> 32));
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		Friend other = (Friend) obj;
		if (friendID != other.friendID)
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
    
}

