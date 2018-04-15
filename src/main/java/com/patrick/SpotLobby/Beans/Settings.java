package com.patrick.SpotLobby.Beans;

import javax.persistence.*;

@Entity
public class Settings {

    @Id
    @Column(nullable=false, name="SETTINGS_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SETTINGSID_SEQUENCE")
	@SequenceGenerator(name="SETTINGSID_SEQUENCE", sequenceName="SETTINGSID_SEQUENCE")
    private long settingsId;

    @Column(nullable=false, name="QUEUE_LENGTH")
    private long queueLength;

    @Column(name="GENRE_RESTRICTION")
    private String genreRestriction;

    @Column(nullable=false, name="IS_LOBBY_OPEN")
    private boolean isLobbyOpen;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, name="PARTY_PRIVACY")
    private PartyPrivacy partyPrivacy;

    @Column(nullable=false, name="IS_VOTE_SYSTEM_ON")
    private boolean isVoteSystemOn;

    public Settings(long settingsId, long queueLength, String genreRestriction, boolean isLobbyOpen,
                    PartyPrivacy partyPrivacy, boolean isVoteSystemOn) {
        super();
        this.settingsId = settingsId;
        this.queueLength = queueLength;
        this.genreRestriction = genreRestriction;
        this.isLobbyOpen = isLobbyOpen;
        this.partyPrivacy = partyPrivacy;
        this.isVoteSystemOn = isVoteSystemOn;
    }
    
    public Settings(long queueLength, String genreRestriction, boolean isLobbyOpen, PartyPrivacy partyPrivacy,
			boolean isVoteSystemOn) {
		super();
		this.queueLength = queueLength;
		this.genreRestriction = genreRestriction;
		this.isLobbyOpen = isLobbyOpen;
		this.partyPrivacy = partyPrivacy;
		this.isVoteSystemOn = isVoteSystemOn;
	}



	public Settings() {
		super();
	}

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

    public PartyPrivacy getPartyPrivacy() {
        return partyPrivacy;
    }

    public void setPartyPrivacy(PartyPrivacy partyPrivacy) {
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
//        if(str == "Open"){
//            setLobbyOpen(true);
//            setPartyPrivacy("OPEN");
//        }
//        else if(str == "Friends"){
//            setLobbyOpen(true);
//            setPartyPrivacy("FRIENDS");
//        }
//        else{
//            setLobbyOpen(false);
//            setPartyPrivacy("CLOSED");
//        }
    }

    /**
     * Turn on the vote system, this object is owned by the lobby itself, it's just
     * toggled through the settings object
     */
    public void turnOnVoteSystem(){
        setVoteSystemOn(true);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genreRestriction == null) ? 0 : genreRestriction.hashCode());
		result = prime * result + (isLobbyOpen ? 1231 : 1237);
		result = prime * result + (isVoteSystemOn ? 1231 : 1237);
		result = prime * result + ((partyPrivacy == null) ? 0 : partyPrivacy.hashCode());
		result = prime * result + (int) (queueLength ^ (queueLength >>> 32));
		result = prime * result + (int) (settingsId ^ (settingsId >>> 32));
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
		Settings other = (Settings) obj;
		if (genreRestriction == null) {
			if (other.genreRestriction != null)
				return false;
		} else if (!genreRestriction.equals(other.genreRestriction))
			return false;
		if (isLobbyOpen != other.isLobbyOpen)
			return false;
		if (isVoteSystemOn != other.isVoteSystemOn)
			return false;
		if (partyPrivacy == null) {
			if (other.partyPrivacy != null)
				return false;
		} else if (!partyPrivacy.equals(other.partyPrivacy))
			return false;
		if (queueLength != other.queueLength)
			return false;
		if (settingsId != other.settingsId)
			return false;
		return true;
	}

}

