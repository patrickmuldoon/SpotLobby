package com.patrick.SpotLobby.Services;

import java.util.List;

import com.patrick.SpotLobby.Beans.Friend;


public interface FriendService {
	
	List<Friend> findAll();
	
	Friend getById(long id);
	
	Friend saveOrUpdate(Friend friend);
	
	void delete(long id);
	
}
