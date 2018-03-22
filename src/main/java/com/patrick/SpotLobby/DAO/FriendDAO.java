package com.patrick.SpotLobby.DAO;

import org.springframework.data.repository.CrudRepository;

import com.patrick.SpotLobby.Beans.Friend;


public interface FriendDAO extends CrudRepository<Friend, Long>{
	
}