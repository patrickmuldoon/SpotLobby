package com.patrick.SpotLobby.Beans;


import java.time.Duration;
import java.util.Date;

import javax.persistence.*;

@Entity
public class SpotifyAuthentication {

    @Id
    @Column(nullable=false, name="SPOTIFY_AUTH_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long spotifyAuthenticaionID;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    private Users users;

    @Column(name="AUTH_TOKEN", nullable=true)
    private String authenticationToken;

    @Column(name="REFRESH_TOKEN", nullable=true)
    private String refreshToken;

    @Column(name="TIME_TOKEN_CREATED", nullable=true)
    private Date timeAuthenticationTokenCreated;

    @Column(name="TIME_TOKEN_MUST_REFRESH", nullable=true)
    private Date timeAuthenticationTokenMustRefresh;

    @Column(name="TIME_LEFT_TILL_REFRESH", nullable=true)
    private Duration timeLeftTillRefresh;

    public SpotifyAuthentication(long spotifyAuthenticaionID, String authenticationToken, String refreshToken,
                                 Date timeAuthenticationTokenCreated, Date timeAuthenticationTokenMustRefresh,
                                 Duration timeLeftTillRefresh) {
        super();
        this.spotifyAuthenticaionID = spotifyAuthenticaionID;
        this.authenticationToken = authenticationToken;
        this.refreshToken = refreshToken;
        this.timeAuthenticationTokenCreated = timeAuthenticationTokenCreated;
        this.timeAuthenticationTokenMustRefresh = timeAuthenticationTokenMustRefresh;
        this.timeLeftTillRefresh = timeLeftTillRefresh;
    }

    public SpotifyAuthentication() {}
    
    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getTimeAuthenticationTokenCreated() {
        return timeAuthenticationTokenCreated;
    }

    public void setTimeAuthenticationTokenCreated(Date timeAuthenticationTokenCreated) {
        this.timeAuthenticationTokenCreated = timeAuthenticationTokenCreated;
    }

    public Date getTimeAuthenticationTokenMustRefresh() {
        return timeAuthenticationTokenMustRefresh;
    }

    public void setTimeAuthenticationTokenMustRefresh(Date timeAuthenticationTokenMustRefresh) {
        this.timeAuthenticationTokenMustRefresh = timeAuthenticationTokenMustRefresh;
    }

    public Duration getTimeLeftTillRefresh() {
        return timeLeftTillRefresh;
    }

    public void setTimeLeftTillRefresh(Duration timeLeftTillRefresh) {
        this.timeLeftTillRefresh = timeLeftTillRefresh;
    }

    public long getSecondsLeftTillRefresh(){
        return this.timeLeftTillRefresh.getSeconds();
    }

    //implement later when authentication has been figured out
    public void refreshAuthToken(){

    }

    /**
     * @return the spotifyAuthenticaionID
     */
    public long getSpotifyAuthenticaionID() {
        return spotifyAuthenticaionID;
    }

    /**
     * @param spotifyAuthenticaionID the spotifyAuthenticaionID to set
     */
    public void setSpotifyAuthenticaionID(long spotifyAuthenticaionID) {
        this.spotifyAuthenticaionID = spotifyAuthenticaionID;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authenticationToken == null) ? 0 : authenticationToken.hashCode());
		result = prime * result + ((refreshToken == null) ? 0 : refreshToken.hashCode());
		result = prime * result + (int) (spotifyAuthenticaionID ^ (spotifyAuthenticaionID >>> 32));
		result = prime * result
				+ ((timeAuthenticationTokenCreated == null) ? 0 : timeAuthenticationTokenCreated.hashCode());
		result = prime * result
				+ ((timeAuthenticationTokenMustRefresh == null) ? 0 : timeAuthenticationTokenMustRefresh.hashCode());
		result = prime * result + ((timeLeftTillRefresh == null) ? 0 : timeLeftTillRefresh.hashCode());
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
		SpotifyAuthentication other = (SpotifyAuthentication) obj;
		if (authenticationToken == null) {
			if (other.authenticationToken != null)
				return false;
		} else if (!authenticationToken.equals(other.authenticationToken))
			return false;
		if (refreshToken == null) {
			if (other.refreshToken != null)
				return false;
		} else if (!refreshToken.equals(other.refreshToken))
			return false;
		if (spotifyAuthenticaionID != other.spotifyAuthenticaionID)
			return false;
		if (timeAuthenticationTokenCreated == null) {
			if (other.timeAuthenticationTokenCreated != null)
				return false;
		} else if (!timeAuthenticationTokenCreated.equals(other.timeAuthenticationTokenCreated))
			return false;
		if (timeAuthenticationTokenMustRefresh == null) {
			if (other.timeAuthenticationTokenMustRefresh != null)
				return false;
		} else if (!timeAuthenticationTokenMustRefresh.equals(other.timeAuthenticationTokenMustRefresh))
			return false;
		if (timeLeftTillRefresh == null) {
			if (other.timeLeftTillRefresh != null)
				return false;
		} else if (!timeLeftTillRefresh.equals(other.timeLeftTillRefresh))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	
}