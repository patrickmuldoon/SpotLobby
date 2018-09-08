package com.patrick.SpotLobby.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patrick.SpotLobby.Beans.Followers;

@Service
public interface FollowersService {
	
	List<Followers> listAll();

	Followers getById(long id);
	
	Followers getByUserIDAndFollowerID(long userID, long followerID);
	
	Followers saveOrUpdate(Followers followers);
	
	Followers saveOrUpdate(long userID, long followedID);
	
	List<Followers> findAllFollowersByUser(long id);
	
	Followers unfollowUser(long userID, long followedID);
	
	void delete(long id);
}
