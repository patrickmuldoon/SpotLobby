package com.patrick.SpotLobby.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.patrick.SpotLobby.Beans.Followers;
import com.patrick.SpotLobby.DAO.FollowersDAO;

public class FollowersServiceImpl implements FollowersService{

	private FollowersDAO followersDAO;
	
	@Autowired
	public FollowersServiceImpl(FollowersDAO followersDAO) {
		this.followersDAO = followersDAO;
	}
	
	@Override
	public List<Followers> listAll() {
		List<Followers> followers = new ArrayList<Followers>();
		followers = (List<Followers>) followersDAO.findAll();
		return followers;
	}

	@Override
	public Followers getById(long id) {
		return followersDAO.findById(id).orElse(null);
	}

	@Override
	public Followers saveOrUpdate(Followers followers) {
		return followersDAO.save(followers);
	}

	@Override
	public void delete(long id) {
		followersDAO.deleteById(id);
	}

	@Override
	public List<Followers> findAllFollowersByUser(long id) {
		List<Followers> followers = new ArrayList<Followers>();
		followers = followersDAO.findAllFollowersByUserID(id);
		return followers;
	}

	@Override
	public Followers getByUseIDAndFollowerID(long userID, long followerID) {
		return followersDAO.findFollowerByUserIDAndFollowerID(userID, followerID);
	}

}
