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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {"Follower_ID", "Followed_ID"}))
public class Followers implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
    @Column(nullable=false, name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="Follower_ID", nullable=false)
	private Users follower;
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="Followed_ID", nullable=false)
	private Users followed;
	
	public Followers(Users followers, Users followed) {
		super();
		this.follower = followers;
		this.followed = followed;
	}

	public Followers(Users followers) {
		super();
		this.follower = followers;
	}

	public Followers() {
		super();
	}

	public Users getFollowers() {
		return follower;
	}

	public void setFollowers(Users followers) {
		this.follower = followers;
	}

	public Users getFollowed() {
		return followed;
	}

	public void setFollowed(Users followed) {
		this.followed = followed;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((followed == null) ? 0 : followed.hashCode());
		result = prime * result + ((follower == null) ? 0 : follower.hashCode());
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
		Followers other = (Followers) obj;
		if (followed == null) {
			if (other.followed != null)
				return false;
		} else if (!followed.equals(other.followed))
			return false;
		if (follower == null) {
			if (other.follower != null)
				return false;
		} else if (!follower.equals(other.follower))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
}
