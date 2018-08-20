package com.patrick.SpotLobby.Beans;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(uniqueConstraints=@UniqueConstraint(columnNames= {"userid"}))
@Entity
public class Profile{
//change all sequences can't just change one
	
    @Id
    @Column(nullable=false, name="ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROFILEID_SEQUENCE")
	@SequenceGenerator(name="PROFILEID_SEQUENCE", sequenceName="PROFILEID_SEQUENCE")
    private long id;
    
    @JsonIgnore
    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    private Users userid;
    
    private String bio;

    @Column(name="PROFILE_PICTURE")
    @Lob
    private byte[] image;

	public Profile(long id, Users userid, String bio, byte[] image) {
		super();
		this.id = id;
		this.userid = userid;
		this.bio = bio;
		this.image = image;
	}

	public Profile() {
		super();
	}

	public Profile(Users userid, String bio) {
		super();
		this.userid = userid;
		this.bio = bio;
	}

	/**
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Users getUserid() {
		return userid;
	}

	public void setUserid(Users userid) {
		this.userid = userid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		Profile other = (Profile) obj;
		if (bio == null) {
			if (other.bio != null)
				return false;
		} else if (!bio.equals(other.bio))
			return false;
		if (id != other.id)
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}
	
}
