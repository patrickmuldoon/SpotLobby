package com.patrick.SpotLobby.Beans;

import java.util.HashMap;

import javax.persistence.*;


@Entity
public class VoteSystem {

    @Id
    @Column(nullable=false, name="VOTE_SYSTEM_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VOTESYSTEMID_SEQUENCE")
	@SequenceGenerator(name="VOTESYSTEMID_SEQUENCE", sequenceName="VOTESYSTEMID_SEQUENCE")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentVotes == null) ? 0 : currentVotes.hashCode());
		result = prime * result + ((lobby == null) ? 0 : lobby.hashCode());
		result = prime * result + (int) (voteSystemId ^ (voteSystemId >>> 32));
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
		VoteSystem other = (VoteSystem) obj;
		if (currentVotes == null) {
			if (other.currentVotes != null)
				return false;
		} else if (!currentVotes.equals(other.currentVotes))
			return false;
		if (lobby == null) {
			if (other.lobby != null)
				return false;
		} else if (!lobby.equals(other.lobby))
			return false;
		if (voteSystemId != other.voteSystemId)
			return false;
		return true;
	}

}
