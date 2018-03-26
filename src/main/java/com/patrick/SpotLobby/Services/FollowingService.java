package com.patrick.SpotLobby.Services;

import java.util.List;

import com.patrick.SpotLobby.Beans.Following;


public interface FollowingService {

	
	List<Following> listAll();

	Following getById(long id);
	
	Following getByUserIDAndFollowingID(long userID, long followerID);
	
	Following saveOrUpdate(Following following);
	
	List<Following> findAllFollowingByUser(long id);
	
	void delete(long id);
}
