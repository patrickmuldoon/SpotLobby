package com.patrick.SpotLobby.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrick.SpotLobby.Beans.Posts;
import com.patrick.SpotLobby.DAO.PostsDAO;

@Service
public class PostsServiceImpl implements PostsService {
	
	@Autowired
	private PostsDAO postDAO;
	
	public void setPostDAO(PostsDAO postDAO) {
		this.postDAO = postDAO;
	}

	@Override
	public List<Posts> listAll() {
		List<Posts> posts = new ArrayList<Posts>();
		postDAO.findAll().forEach(posts::add);
		return posts;
	}

	@Override
	public List<Posts> findByUserID(long userID) {
		List<Posts> posts = new ArrayList<Posts>();
		postDAO.findPostsByUserID(userID).forEach(posts::add);
		return posts;
	}

	@Override
	public Posts saveOrUpdate(Posts post) {
		postDAO.save(post);
		return post;
	}

	@Override
	public void deleteByID(long id) {
		postDAO.deleteById(id);
	}

}
