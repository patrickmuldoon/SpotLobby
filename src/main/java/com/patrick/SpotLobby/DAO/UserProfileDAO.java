package com.patrick.SpotLobby.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.patrick.SpotLobby.Beans.Profile;

@Repository
public class UserProfileDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Profile findByuserid(long userid) {
		Profile userProf = entityManager.createQuery("select p from Profile p join Users u on u.userID = p.userid where u.userID = :user_id", Profile.class)
				.setParameter("user_id", userid).getSingleResult();
		return userProf;
	}
	
}
