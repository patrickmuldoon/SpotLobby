package com.patrick.SpotLobby.Beans;


import java.util.Set;

public class Voter {

    private int likes;

    private int dislikes;

    private Set<Long> usersWhoUpVoted;

    private Set<Long> usersWhoDownVoted;

    public Voter(int likes, int dislikes) {
        super();
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Set<Long> getUsersWhoUpVoted() {
        return usersWhoUpVoted;
    }

    public void setUsersWhoUpVoted(Set<Long> usersWhoUpVoted) {
        this.usersWhoUpVoted = usersWhoUpVoted;
    }

    public Set<Long> getUsersWhoDownVoted() {
        return usersWhoDownVoted;
    }

    public void setUsersWhoDownVoted(Set<Long> usersWhoDownVoted) {
        this.usersWhoDownVoted = usersWhoDownVoted;
    }

    public void userUpVotedSong(Users user){
        if(checkIfUserUpVoted(user)){
            return;
        }
        else if(checkIfUserDownVoted(user)){
            usersWhoDownVoted.remove(user.getUserID());
            this.minusOneDislike();
            usersWhoUpVoted.add(user.getUserID());
            this.addOneLike();
        }
        else{
            usersWhoUpVoted.add(user.getUserID());
            this.addOneLike();
        }
    }

    public void userDownVotedSong(Users user){
        if(checkIfUserDownVoted(user)){
            return;
        }
        else if(checkIfUserUpVoted(user)){
            usersWhoUpVoted.remove(user.getUserID());
            this.minusOneLike();
            usersWhoDownVoted.add(user.getUserID());
            this.addOneDislike();
        }
        else{
            usersWhoDownVoted.add(user.getUserID());
            this.addOneLike();
        }
    }

    public boolean checkIfUserUpVoted(Users user){
        return usersWhoUpVoted.contains(user.getUserID());
    }

    public boolean checkIfUserDownVoted(Users user){
        return usersWhoDownVoted.contains(user.getUserID());
    }

    public void addOneLike(){
        this.likes++;
    }

    public void addOneDislike(){
        this.dislikes++;
    }

    public void minusOneLike(){
        this.likes--;
    }

    public void minusOneDislike(){
        this.dislikes--;
    }
}
