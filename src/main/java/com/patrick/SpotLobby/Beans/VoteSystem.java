package com.patrick.SpotLobby.Beans;

import java.util.HashMap;

import javax.persistence.*;


@Entity
@Table(name="VOTE_SYSTEM")
public class VoteSystem {

    @Id
    @Column(nullable=false, name="VOTE_SYSTEM_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long voteSystemId;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    private Lobby lobby;

    private HashMap<Integer, SongInfo> currentVotes;

    public VoteSystem() {}
    
    public VoteSystem(long voteSystemId, Lobby lobby, HashMap<Integer, SongInfo> currentVotes) {
        super();
        this.voteSystemId = voteSystemId;
        this.lobby = lobby;
        this.currentVotes = currentVotes;
    }

    public long getVoteSystemId() {
        return voteSystemId;
    }

    public void setVoteSystemId(long voteSystemId) {
        this.voteSystemId = voteSystemId;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public HashMap<Integer, SongInfo> getCurrentVotes() {
        return currentVotes;
    }

    public void setCurrentVotes(HashMap<Integer, SongInfo> currentVotes) {
        this.currentVotes = currentVotes;
    }


}
