package com.patrick.SpotLobby.Beans;


import java.time.Duration;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="SPOTIFY_AUTH")
public class SpotifyAuthentication {


    @Id
    @Column(nullable=false, name="SPOTIFY_AUTH_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long spotifyAuthenticaionID;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    private Users user;

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



}