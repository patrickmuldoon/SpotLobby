package com.patrick.SpotLobby.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrick.SpotLobby.Beans.Posts;
import com.patrick.SpotLobby.Beans.Profile;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.DAO.PostsCrudDAO;
import com.patrick.SpotLobby.DAO.PostsDAO;

@Service
public class PostsServiceImpl implements PostsService {
	
	@Autowired
	private PostsCrudDAO postCrudDAO;
	
	@Autowired
	private PostsDAO postDAO;
	
	public void setpostCrudDAO(PostsCrudDAO postCrudDAO) {
		this.postCrudDAO = postCrudDAO;
	}
	
	public void setPostDAO(PostsDAO postDAO) {
		this.postDAO = postDAO;
	}

	@Override
	public List<Posts> listAll() {
		List<Posts> posts = new ArrayList<Posts>();
		postCrudDAO.findAll().forEach(posts::add);
		for(Posts post : posts) {
			Users user = new Users(post.getMessageOwner().getUserID(), post.getMessageOwner().getFirstName(),
					post.getMessageOwner().getLastName(), post.getMessageOwner().getUsername(), post.getMessageOwner().getUserProfile());
			post.setMessageOwner(user);
		}
		return posts;
	}

	@Override
	public List<Posts> findByUserID(long userID) {
		List<Posts> posts = new ArrayList<Posts>();
		postCrudDAO.findPostsByUserID(userID).forEach(posts::add);
		return posts;
	}

	@Override
	public Posts saveOrUpdate(Posts post) {
		postCrudDAO.save(post);
		return post;
	}

	@Override
	public void deleteByID(long id) {
		postCrudDAO.deleteById(id);
	}

	@Override
	public Posts findById(long postID) {
		Posts post = postCrudDAO.findById(postID).orElse(null);
		return post;
	}

	@Override
	public List<Posts> listAllWithUsers() {
		List<Posts> posts = new ArrayList<Posts>();
		postDAO.findAllPostsWithUsers().forEach(posts::add);
		for(Posts post : posts) {
			Profile profile = new Profile();
			if(post.getMessageOwner().getUserProfile().getImage() == null) {
				File file = new File("src/main/resources/img/defaultProfileImg.jpg");
				byte[] img;
				try {
					img = Files.readAllBytes(file.toPath());
					profile.setImage(img);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else
				profile.setImage(post.getMessageOwner().getUserProfile().getImage());
			Users user = new Users(post.getMessageOwner().getUserID(), post.getMessageOwner().getFirstName(),
					post.getMessageOwner().getLastName(), post.getMessageOwner().getUsername(), profile);
			post.setMessageOwner(user);
		}
		return posts;
	}
	
}
