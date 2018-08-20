package com.patrick.SpotLobby.Beans;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Posts {

	@Id
    @Column(nullable=false, name="ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POSTID_SEQUENCE")
	@SequenceGenerator(name="POSTID_SEQUENCE", sequenceName="POSTID_SEQUENCE")
    private long id;
	
	@Column(name="MESSAGE", nullable=false)
	private String message;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="USERID", nullable=false)
	private Users messageOwner;
	
	private long upvotes;
	
	private long downvotes;
	
	@Column(name="TIME_MESSAGE_CREATED", nullable=false)
	private Timestamp timeMessageCreated;
	
	@Column(name="TIME_MESSAGE_LAST_EDITED")
	private Timestamp timeMessageLastEdited;

	public Posts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Posts(long id, String message, Users messageOwner, long upvotes, long downvotes,
			Timestamp timeMessageCreated, Timestamp timeMessageLastEdited) {
		super();
		this.id = id;
		this.message = message;
		this.messageOwner = messageOwner;
		this.upvotes = upvotes;
		this.downvotes = downvotes;
		this.timeMessageCreated = timeMessageCreated;
		this.timeMessageLastEdited = timeMessageLastEdited;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Users getMessageOwner() {
		return messageOwner;
	}

	public void setMessageOwner(Users messageOwner) {
		this.messageOwner = messageOwner;
	}

	public long getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(long upvotes) {
		this.upvotes = upvotes;
	}

	public long getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(long downvotes) {
		this.downvotes = downvotes;
	}

	public Timestamp getTimeMessageCreated() {
		return timeMessageCreated;
	}

	public void setTimeMessageCreated(Timestamp timeMessageCreated) {
		this.timeMessageCreated = timeMessageCreated;
	}

	public Timestamp getTimeMessageLastEdited() {
		return timeMessageLastEdited;
	}

	public void setTimeMessageLastEdited(Timestamp timeMessageLastEdited) {
		this.timeMessageLastEdited = timeMessageLastEdited;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (downvotes ^ (downvotes >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((messageOwner == null) ? 0 : messageOwner.hashCode());
		result = prime * result + ((timeMessageCreated == null) ? 0 : timeMessageCreated.hashCode());
		result = prime * result + ((timeMessageLastEdited == null) ? 0 : timeMessageLastEdited.hashCode());
		result = prime * result + (int) (upvotes ^ (upvotes >>> 32));
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
		Posts other = (Posts) obj;
		if (downvotes != other.downvotes)
			return false;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageOwner == null) {
			if (other.messageOwner != null)
				return false;
		} else if (!messageOwner.equals(other.messageOwner))
			return false;
		if (timeMessageCreated == null) {
			if (other.timeMessageCreated != null)
				return false;
		} else if (!timeMessageCreated.equals(other.timeMessageCreated))
			return false;
		if (timeMessageLastEdited == null) {
			if (other.timeMessageLastEdited != null)
				return false;
		} else if (!timeMessageLastEdited.equals(other.timeMessageLastEdited))
			return false;
		if (upvotes != other.upvotes)
			return false;
		return true;
	}
	
	
}
