package com.patrick.SpotLobby.Beans;

import java.util.Date;

public class SongInfo {

    private long songID;

    private String songName;

    private String artistName;

    private String albumName;

    private int songDuration; //stored in milliseconds, will put output in MM:SS

    private Date timeSongAdded;

    private Voter voter;

    public SongInfo(int songID, String songName, String artistName, String albumName, int songDuration, Date timeSongAdded,
                    Voter voter) {
        super();
        this.songID = songID;
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.songDuration = songDuration;
        this.timeSongAdded = timeSongAdded;
        this.voter = voter;
    }
    
    public SongInfo() {}

    public long getSongID() {
        return songID;
    }

    public void setSongID(long songID) {
        this.songID = songID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(int songDuration) {
        this.songDuration = songDuration;
    }

    public Date getTimeSongAdded() {
        return timeSongAdded;
    }

    public void setTimeSongAdded(Date timeSongAdded) {
        this.timeSongAdded = timeSongAdded;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public void likedASong(){
        voter.addOneLike();
    }

    public void dislikedASong(){
        voter.addOneDislike();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((albumName == null) ? 0 : albumName.hashCode());
        result = prime * result + ((artistName == null) ? 0 : artistName.hashCode());
        result = prime * result + songDuration;
        result = prime * result + (int) (songID ^ (songID >>> 32));
        result = prime * result + ((songName == null) ? 0 : songName.hashCode());
        result = prime * result + ((timeSongAdded == null) ? 0 : timeSongAdded.hashCode());
        result = prime * result + ((voter == null) ? 0 : voter.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SongInfo other = (SongInfo) obj;
        if (albumName == null) {
            if (other.albumName != null)
                return false;
        } else if (!albumName.equals(other.albumName))
            return false;
        if (artistName == null) {
            if (other.artistName != null)
                return false;
        } else if (!artistName.equals(other.artistName))
            return false;
        if (songDuration != other.songDuration)
            return false;
        if (songID != other.songID)
            return false;
        if (songName == null) {
            if (other.songName != null)
                return false;
        } else if (!songName.equals(other.songName))
            return false;
        if (timeSongAdded == null) {
            if (other.timeSongAdded != null)
                return false;
        } else if (!timeSongAdded.equals(other.timeSongAdded))
            return false;
        if (voter == null) {
            if (other.voter != null)
                return false;
        } else if (!voter.equals(other.voter))
            return false;
        return true;
    }

}
