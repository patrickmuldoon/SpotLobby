package com.patrick.SpotLobby.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.patrick.SpotLobby.Beans.Posts;
import com.patrick.SpotLobby.Services.PostsService;

@Controller
public class PostsController {
	
	@Autowired
	private PostsService postService;

	public void setPostService(PostsService postService) {
		this.postService = postService;
	}

	@RequestMapping(value="/posts/createNewPost", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Posts> createPost(@Valid @RequestBody Posts post){
		return new ResponseEntity<Posts>(null);
	}

}
