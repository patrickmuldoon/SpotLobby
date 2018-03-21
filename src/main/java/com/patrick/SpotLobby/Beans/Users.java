package com.patrick.SpotLobby.Beans;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.*;
//map the database next, then try server again
@Entity
public class Users {

    @Id
    @Column(nullable=false, name="USERS_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long userID;

    @Column(name="FIRST_NAME", nullable=false)
    private String firstName;

    @Column(name="LAST_NAME", nullable=false)
    private String lastName;

    @Column(name="USERNAME", nullable=false, unique=true)
    private String username;

    @Column(name="PASSWORD", nullable=false)
    private String password;

    @Column(name="EMAIL", nullable=false, unique=true)
    private String email;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Contacts> contactList;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    private Lobby lobby;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    private Profile userProfile;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    private Settings userSettings;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    private SpotifyAuthentication spotifyAuthentication;

    public Users(long userID, String firstName, String lastName, String username, String password, String email,
                 Set<Contacts> contactList, Lobby lobby, Profile userProfile, Settings userSettings,
                 SpotifyAuthentication spotifyAuth) {
        super();
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.contactList = contactList;
        this.lobby = lobby;
        this.userProfile = userProfile;
        this.userSettings = userSettings;
        this.spotifyAuthentication = spotifyAuth;
    }
    
    public Users(String firstName, String lastName, String userName, String password, String email) {
    		this.firstName = firstName;
    		this.lastName = lastName;
    		this.username = userName;
    		this.password = password;
    		this.email = email;
    }

    public Users() {}

    public long getUserID() {
        return userID;
    }


    public void setUserID(int userID) {
        this.userID = userID;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Set<Contacts> getContactList() {
        return contactList;
    }


    public void setContactList(Set<Contacts> contactList) {
        this.contactList = contactList;
    }


    public Lobby getLobby() {
        return lobby;
    }


    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }


    public Profile getUserProfile() {
        return userProfile;
    }


    public void setUserProfile(Profile userProfile) {
        this.userProfile = userProfile;
    }


    public Settings getUserSettings() {
        return userSettings;
    }


    public void setUserSettings(Settings userSettings) {
        this.userSettings = userSettings;
    }


    public SpotifyAuthentication getSpotifyAuth() {
        return spotifyAuthentication;
    }


    public void setSpotifyAuth(SpotifyAuthentication spotifyAuth) {
        this.spotifyAuthentication = spotifyAuth;
    }   
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contactList == null) ? 0 : contactList.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((lobby == null) ? 0 : lobby.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((spotifyAuthentication == null) ? 0 : spotifyAuthentication.hashCode());
		result = prime * result + (int) (userID ^ (userID >>> 32));
		result = prime * result + ((userProfile == null) ? 0 : userProfile.hashCode());
		result = prime * result + ((userSettings == null) ? 0 : userSettings.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Users other = (Users) obj;
		if (contactList == null) {
			if (other.contactList != null)
				return false;
		} else if (!contactList.equals(other.contactList))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (lobby == null) {
			if (other.lobby != null)
				return false;
		} else if (!lobby.equals(other.lobby))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (spotifyAuthentication == null) {
			if (other.spotifyAuthentication != null)
				return false;
		} else if (!spotifyAuthentication.equals(other.spotifyAuthentication))
			return false;
		if (userID != other.userID)
			return false;
		if (userProfile == null) {
			if (other.userProfile != null)
				return false;
		} else if (!userProfile.equals(other.userProfile))
			return false;
		if (userSettings == null) {
			if (other.userSettings != null)
				return false;
		} else if (!userSettings.equals(other.userSettings))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Entity
    @Table(name="USER_PROFILE")
    private class Profile{

        @Id
        @Column(nullable=false, name="USER_PROFILE_ID")
        @GeneratedValue(strategy=GenerationType.AUTO)
        private long userProfileID;

        private String bio;

        @Column(name="PROFILE_PICTURE")
        @Lob
        private byte[] image;

        public Profile(long userProfileId, String bio, byte[] image) {
            super();
            this.userProfileID = userProfileId;
            this.bio = bio;
            this.image = image;
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

        /**
         * @return the userProfileID
         */
        public long getUserProfileID() {
            return userProfileID;
        }

        /**
         * @param userProfileID the userProfileID to set
         */
        public void setUserProfileID(long userProfileID) {
            this.userProfileID = userProfileID;
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((bio == null) ? 0 : bio.hashCode());
			result = prime * result + Arrays.hashCode(image);
			result = prime * result + (int) (userProfileID ^ (userProfileID >>> 32));
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
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (bio == null) {
				if (other.bio != null)
					return false;
			} else if (!bio.equals(other.bio))
				return false;
			if (!Arrays.equals(image, other.image))
				return false;
			if (userProfileID != other.userProfileID)
				return false;
			return true;
		}

		private Users getOuterType() {
			return Users.this;
		}
        
    }
}
