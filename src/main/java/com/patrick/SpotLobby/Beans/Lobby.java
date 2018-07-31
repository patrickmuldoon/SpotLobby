package com.patrick.SpotLobby.Beans;

import java.io.IOException;
import java.net.ServerSocket;

import javax.persistence.*;

@Entity
public class Lobby {

    @Id
    @Column(nullable=false, name="LOBBY_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOBBYID_SEQUENCE")
	@SequenceGenerator(name="LOBBYID_SEQUENCE", sequenceName="LOBBYID_SEQUENCE")
    private long lobbyId;

//	private ServerSocket lobbyServer;

    @Column(name="IS_SERVER_ON", nullable=false)
    private boolean isServerOn;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    private Users lobbyOwner;

//	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
//	private Users lobbyHost;

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Settings lobbySettings;

    @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
    private VoteSystem voteSystem;

//	//Will assess type later
//	private PlayQueue queue;

    public Lobby(long lobbyId, boolean isServerOn, Users lobbyOwner, Settings lobbySettings, VoteSystem voteSystem,
                 PlayQueue queue) {
        super();
        this.lobbyId = lobbyId;
        this.isServerOn = isServerOn;
        this.lobbyOwner = lobbyOwner;
//		this.lobbyHost = lobbyOwner;
        this.lobbySettings = lobbySettings;
        this.voteSystem = voteSystem;
//		this.queue = queue;
    }
    
    public Lobby(boolean isServerOn, Users lobbyOwner) {
		super();
		this.isServerOn = isServerOn;
		this.lobbyOwner = lobbyOwner;
	}

	public Lobby() {
    		super();
    }

    public long getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(long lobbyId) {
        this.lobbyId = lobbyId;
    }

//	public ServerSocket getLobbyServer() {
//		return lobbyServer;
//	}
//
//	public void setLobbyServer(ServerSocket lobbyServer) {
//		this.lobbyServer = lobbyServer;
//	}

    public boolean isServerOn() {
        return isServerOn;
    }

    public void setServerOn(boolean isServerOn) {
        this.isServerOn = isServerOn;
    }

    public Users getLobbyOwner() {
        return lobbyOwner;
    }

    public void setLobbyOwner(Users lobbyOwner) {
        this.lobbyOwner = lobbyOwner;
    }

//	public Users getLobbyHost() {
//		return lobbyHost;
//	}
//
//	public void setLobbyHost(Users lobbyHost) {
//		this.lobbyHost = lobbyHost;
//	}

    public Settings getLobbySettings() {
        return lobbySettings;
    }

    public void setLobbySettings(Settings lobbySettings) {
        this.lobbySettings = lobbySettings;
    }

    public VoteSystem getVoteSystem() {
        return voteSystem;
    }

    public void setVoteSystem(VoteSystem voteSystem) {
        this.voteSystem = voteSystem;
    }

//	public PlayQueue getQueue() {
//		return queue;
//	}
//
//	public void setQueue(PlayQueue queue) {
//		this.queue = queue;
//	}

    /**
     * Creates a new serverSocket and gives it to the lobby to use.
     * The server will stay on awaiting connections until the turnServerOff()
     * function is called
     * @throws IOException
     */
//	public void turnServerOn() throws IOException{
//
//		Runnable serverTask = new Runnable() {
//
//			@Override
//			public void run() {
//				try {
//					setLobbyServer(new ServerSocket());
//					while(isServerOn()){
//						Thread.sleep(10000);
//					}
//					lobbyServer.close();
//				} catch (Exception e) {
//					System.err.print("could not connect client socket");
//					e.printStackTrace();
//				}
//			}
//		};
//		Thread serverThread = new Thread(serverTask);
//		serverThread.start();
//	}

    /**
     * Turns the boolean for isServerOn to false. This should force the while
     * loop above to exit, ultimately closing down the server
     */
    public void turnServerOff() {
        setServerOn(false);
    }

    /**
     * Change host of lobby to a new User
     * Ownership of the lobby still belongs to the owner of the lobby,
     * just certain controls of the lobby will be relinquished to the new host
     * @param newHost
     */
//	public void changeLobbyHost(Users newHost){
//		setLobbyHost(newHost);
//	}

    //This is a placeholder for now and will implement how songs will actually
    //be queued in the future
    public void addSong(String song){
        //add implementation
    }

    //Going to have to figure out how this will actually work
    //Already can see a problem if two of the same songs are in the same
    //queue so searching list using Song title will not work (errors when songs have
    //the same title too)*******
    public void removeSong(int index){
        //add implementation
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isServerOn ? 1231 : 1237);
		result = prime * result + (int) (lobbyId ^ (lobbyId >>> 32));
		result = prime * result + ((lobbyOwner == null) ? 0 : lobbyOwner.hashCode());
		result = prime * result + ((lobbySettings == null) ? 0 : lobbySettings.hashCode());
		result = prime * result + ((voteSystem == null) ? 0 : voteSystem.hashCode());
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
		Lobby other = (Lobby) obj;
		if (isServerOn != other.isServerOn)
			return false;
		if (lobbyId != other.lobbyId)
			return false;
		if (lobbyOwner == null) {
			if (other.lobbyOwner != null)
				return false;
		} else if (!lobbyOwner.equals(other.lobbyOwner))
			return false;
		if (lobbySettings == null) {
			if (other.lobbySettings != null)
				return false;
		} else if (!lobbySettings.equals(other.lobbySettings))
			return false;
		if (voteSystem == null) {
			if (other.voteSystem != null)
				return false;
		} else if (!voteSystem.equals(other.voteSystem))
			return false;
		return true;
	}
    
}

