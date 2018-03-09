package com.patrick.SpotLobby.Beans;

import javax.persistence.*;

@Entity
@Table(name="Settings")
public class Settings {

    @Id
    @Column(nullable=false, name="SETTINGS_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long settingsId;

    @Column(nullable=false, name="QUEUE_LENGTH")
    private long queueLength;

    @Column(nullable=false, name="GENRE_RESTRICTION")
    private String genreRestriction;

    @Column(nullable=false, name="IS_LOBBY_OPEN")
    private boolean isLobbyOpen;

    @Column(nullable=false, name="PARTY_PRIVACY")
    private String partyPrivacy;

    @Column(nullable=false, name="IS_VOTE_SYSTEM_ON")
    private boolean isVoteSystemOn;

    public Settings(long settingsId, long queueLength, String genreRestriction, boolean isLobbyOpen,
                    String partyPrivacy, boolean isVoteSystemOn) {
        super();
        this.settingsId = settingsId;
        this.queueLength = queueLength;
        this.genreRestriction = genreRestriction;
        this.isLobbyOpen = isLobbyOpen;
        this.partyPrivacy = partyPrivacy;
        this.isVoteSystemOn = isVoteSystemOn;
    }
    
    public Settings() {}

    public long getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(long settingsId) {
        this.settingsId = settingsId;
    }

    public long getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(long queueLength) {
        this.queueLength = queueLength;
    }

    public String getGenreRestriction() {
        return genreRestriction;
    }

    public void setGenreRestriction(String genreRestriction) {
        this.genreRestriction = genreRestriction;
    }

    public boolean isLobbyOpen() {
        return isLobbyOpen;
    }

    public void setLobbyOpen(boolean isLobbyOpen) {
        this.isLobbyOpen = isLobbyOpen;
    }

    public String getPartyPrivacy() {
        return partyPrivacy;
    }

    public void setPartyPrivacy(String partyPrivacy) {
        this.partyPrivacy = partyPrivacy;
    }

    public boolean isVoteSystemOn() {
        return isVoteSystemOn;
    }

    public void setVoteSystemOn(boolean isVoteSystemOn) {
        this.isVoteSystemOn = isVoteSystemOn;
    }

    /**
     * Change the party privacy settings to allow anyone, just friends
     * or noone at all :)
     * @param str
     */
    public void changePartyPrivacy(String str){
        if(str == "Open"){
            setLobbyOpen(true);
            setPartyPrivacy("OPEN");
        }
        else if(str == "Friends"){
            setLobbyOpen(true);
            setPartyPrivacy("FRIENDS");
        }
        else{
            setLobbyOpen(false);
            setPartyPrivacy("CLOSED");
        }
    }

    /**
     * Turn on the vote system, this object is owned by the lobby itself, it's just
     * toggled through the settings object
     */
    public void turnOnVoteSystem(){
        setVoteSystemOn(true);
    }

}

