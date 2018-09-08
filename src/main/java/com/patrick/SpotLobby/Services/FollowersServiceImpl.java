package com.patrick.SpotLobby.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.patrick.SpotLobby.Beans.Followers;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.DAO.FollowersCrudDAO;
import com.patrick.SpotLobby.DAO.UsersDAO;

public class FollowersServiceImpl implements FollowersService{

	private FollowersCrudDAO followersCrudDAO;
	
	private UsersDAO userDAO;
	
	@Autowired
	public FollowersServiceImpl(FollowersCrudDAO followersCrudDAO, UsersDAO userDAO) {
		this.followersCrudDAO = followersCrudDAO;
		this.userDAO = userDAO;
	}
	
	@Override
	public List<Followers> listAll() {
		List<Followers> followers = new ArrayList<Followers>();
		followers = (List<Followers>) followersCrudDAO.findAll();
		return followers;
	}

	@Override
	public Followers getById(long id) {
		return followersCrudDAO.findById(id).orElse(null);
	}

	@Override
	public Followers saveOrUpdate(Followers followers) {
		return followersCrudDAO.save(followers);
	}
	
	

	//rework this one, it is possible to delete record by primary id possibly, but there is 
	//a unique constraint on both userID's in each entry
	@Override
	public void delete(long id) {
		followersCrudDAO.deleteById(id);
	}
	

	@Override
	public List<Followers> findAllFollowersByUser(long id) {
		List<Followers> followers = new ArrayList<Followers>();
		followers = followersCrudDAO.findAllFollowersByUserID(id);
		return followers;
	}

	@Override
	public Followers getByUserIDAndFollowerID(long userID, long followerID) {
		return followersCrudDAO.findFollowerByUserIDAndFollowerID(userID, followerID);
	}

	/**
	 * Follower Table: following_ID = followedID, follower_ID = userID
	 * following_ID corresponds to the user that is being followed currently, so userID is a follower of following_ID,
	 */
	@Override
	public Followers unfollowUser(long userID, long followedID) {
		long followerID = 0;
		Users user = userDAO.findById(userID).orElse(null);
		Users follower = userDAO.findById(followedID).orElse(null);
		if(user != null && follower != null) {
			Followers followerObject = followersCrudDAO.findFollowerByUserIDAndFollowerID(follower.getUserID(), user.getUserID());
			followersCrudDAO.delete(followerObject);
			followerID = followerObject.getId();
		}
		Followers result = followersCrudDAO.findById(followerID).orElse(null);
		if(result == null)
			return null;
		else 
			return new Followers();
			
	}

	@Override
	public Followers saveOrUpdate(long userID, long followedID) {
		Users user = userDAO.findById(userID).orElse(null);
		Users follower = userDAO.findById(followedID).orElse(null);
		if(user != null && follower != null) {
			Followers followerObject = new Followers();
			followerObject.setFollowers(user);
			followerObject.setFollowing(follower);
			followersCrudDAO.save(followerObject);
			return followerObject;
		}
		return null;
	}

}
