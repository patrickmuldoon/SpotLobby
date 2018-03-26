package com.patrick.SpotLobby.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.patrick.SpotLobby.Beans.Following;
import com.patrick.SpotLobby.DAO.FollowingDAO;

public class FollowingServiceImpl implements FollowingService{

	private FollowingDAO followingDAO;
	
	@Autowired 
	public FollowingServiceImpl(FollowingDAO followingDAO) {
		this.followingDAO = followingDAO;
	}
	
	@Override
	public List<Following> listAll() {
		List<Following> following = new ArrayList<Following>(); 
		following = (List<Following>) followingDAO.findAll();
		return following;
	}

	@Override
	public Following getById(long id) {
		return followingDAO.findById(id).orElse(null);
	}


	@Override
	public Following saveOrUpdate(Following following) {
		return followingDAO.save(following);
	}

	@Override
	public List<Following> findAllFollowingByUser(long id) {
		List<Following> following = new ArrayList<>();
		following = followingDAO.findAllFollowersByUserID(id);
		return following;
	}

	@Override
	public void delete(long id) {
		followingDAO.deleteById(id);
	}

	@Override
	public Following getByUserIDAndFollowingID(long userID, long followerID) {
		return followingDAO.findFollowingByUserIDAndFollowingID(userID, followerID);
	}

}
