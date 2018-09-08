package com.patrick.SpotLobby.DAO;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FollowersDAO {

	@Autowired
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void unfollowerUserByID(long userID, long followedID) {
		entityManager.createQuery("delete from followers where follower_ID = :userID and following_id = :followedID").
			setParameter("userID", userID).setParameter("followedID", followedID).executeUpdate();
	}

}
