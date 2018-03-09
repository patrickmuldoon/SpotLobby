package com.patrick.SpotLobby.Beans;

import java.util.Set;

import javax.persistence.*;
//map the database next, then try server again
@Entity
@Table(name="USERS")
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

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    private Lobby lobby;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    private Profile userProfile;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    private Settings userSettings;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    private SpotifyAuthentication spotifyAuth;

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
        this.spotifyAuth = spotifyAuth;
    }


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
        return spotifyAuth;
    }


    public void setSpotifyAuth(SpotifyAuthentication spotifyAuth) {
        this.spotifyAuth = spotifyAuth;
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



    }
}
