package com.patrick.SpotLobby.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patrick.SpotLobby.Beans.Followers;

@Service
public interface FollowersService {
	
	List<Followers> listAll();

	Followers getById(long id);
	
	Followers getByUseIDAndFollowerID(long userID, long followerID);
	
	Followers saveOrUpdate(Followers followers);
	
	List<Followers> findAllFollowersByUser(long id);
	
	void delete(long id);
}
