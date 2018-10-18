package com.patrick.SpotLobby.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.patrick.SpotLobby.Beans.Posts;

@Repository
public class PostsDAO {

	@Autowired
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<Posts> findAllPostsWithUsers() {
		List<Posts> posts = (List<Posts>) entityManager.createQuery("select p from Posts p join Users u on u.userID = p.messageOwner", Posts.class)
				.getResultList();
		return posts;
	}
}
