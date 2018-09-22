package com.patrick.SpotLobby.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patrick.SpotLobby.Beans.Posts;

@Service
public interface PostsService {

	List<Posts> listAll();
	
	List<Posts> findByUserID(long userID);
	
	Posts saveOrUpdate(Posts post);
	
	void deleteByID(long id);
	
}
