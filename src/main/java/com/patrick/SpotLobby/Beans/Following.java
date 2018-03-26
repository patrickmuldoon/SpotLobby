package com.patrick.SpotLobby.Beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Following implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1039078153723461004L;

	@Id
    @Column(nullable=false, name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="Following_ID", nullable=false)
	private Users followingUser;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="Followed_ID", nullable=false)
	private Users followed;

	public Following(Users followingUser, Users followed) {
		super();
		this.followingUser = followingUser;
		this.followed = followed;
	}

	public Following(long id, Users followingUser, Users followed) {
		super();
		this.id = id;
		this.followingUser = followingUser;
		this.followed = followed;
	}

	public Following(Users followingUser) {
		super();
		this.followingUser = followingUser;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Users getFollowingUser() {
		return followingUser;
	}

	public void setFollowingUser(Users followingUser) {
		this.followingUser = followingUser;
	}

	public Users getFollowed() {
		return followed;
	}

	public void setFollowed(Users followed) {
		this.followed = followed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((followed == null) ? 0 : followed.hashCode());
		result = prime * result + ((followingUser == null) ? 0 : followingUser.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Following other = (Following) obj;
		if (followed == null) {
			if (other.followed != null)
				return false;
		} else if (!followed.equals(other.followed))
			return false;
		if (followingUser == null) {
			if (other.followingUser != null)
				return false;
		} else if (!followingUser.equals(other.followingUser))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
