package com.patrick.SpotLobby.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.patrick.SpotLobby.Beans.Profile;
import com.patrick.SpotLobby.Services.ProfileService;
import com.patrick.SpotLobby.Services.UsersService;

@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@RequestMapping(value="profile/findProfileByUserId/{userID}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Profile> findProfileByUserID(@PathVariable long userID){
		Profile userProf = profileService.findProfileByUserId((long)userID);
		if(userProf != null)
			return new ResponseEntity<Profile>(userProf, HttpStatus.OK);
		else
			return new ResponseEntity<Profile>(HttpStatus.BAD_REQUEST);
	}
}
