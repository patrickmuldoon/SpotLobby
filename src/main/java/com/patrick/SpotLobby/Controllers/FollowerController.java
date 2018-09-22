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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.patrick.SpotLobby.Beans.Followers;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.Services.FollowersService;
import com.patrick.SpotLobby.Services.LoginService;

@Controller
public class FollowerController {
	
	private LoginService loginService;
	
	private FollowersService followerService;
	
	@Autowired
	public FollowerController(LoginService loginService, FollowersService followerService) {
		this.loginService = loginService;
		this.followerService = followerService;
	}
	
	@RequestMapping(value="/followers/followerUser", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Void> followerUser(@Valid @RequestParam long followedID){
		Users user = (Users) loginService.getSession().getAttribute("loggedInUser");
		Followers followerObject = followerService.saveOrUpdate(user.getUserID(), followedID);
		if(followerObject != null) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/followers/unfollowUser", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Void> unfollowUser(@Valid @RequestParam long followedID){
		Users user = (Users) loginService.getSession().getAttribute("loggedInUser");
		Followers follower = followerService.unfollowUser(user.getUserID(), followedID);
		if(follower != null)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

}
