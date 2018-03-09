package com.patrick.SpotLobby.Beans;

import java.util.LinkedList;

public class PlayQueue {

    private LinkedList<SongInfo> smarterQueue;

    public PlayQueue(LinkedList<SongInfo> smarterQueue) {
        super();
        this.smarterQueue = smarterQueue;
    }
    
    public PlayQueue() {}


    public LinkedList<SongInfo> getSmarterQueue() {
        return smarterQueue;
    }


    public void setSmarterQueue(LinkedList<SongInfo> smarterQueue) {
        this.smarterQueue = smarterQueue;
    }


    public void addSongToQueue(SongInfo song){
        this.smarterQueue.addLast(song);
    }

    public void addSongToFrontOfQueue(SongInfo song){
        this.smarterQueue.addFirst(song);
    }

    public void removeSongFromQueue(SongInfo song){
        if(this.smarterQueue.contains(song)){
            this.smarterQueue.remove(song);
        }
    }
}

