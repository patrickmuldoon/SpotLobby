package com.patrick.SpotLobby.Controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.patrick.SpotLobby.Beans.Profile;
import com.patrick.SpotLobby.Beans.UserRoles;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.Services.LoginService;
import com.patrick.SpotLobby.Services.ProfileService;
import com.patrick.SpotLobby.Services.UsersService;

@Controller
public class ProfileController {

	@Autowired
	private UsersService userService;
	
	@Autowired
	private ProfileService profileService;

	@Autowired
	private LoginService loginService;
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="profile/findProfileByUserId/{userID}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Profile> findProfileByUserID(@PathVariable long userID){
		Profile userProf = profileService.findProfileByUserId((long)userID);
		if(userProf != null) {
			return new ResponseEntity<Profile>(userProf, HttpStatus.OK);
		}
		else
			return new ResponseEntity<Profile>(HttpStatus.BAD_REQUEST);
	}
	
	//get Multipart dependencies and map bean 
		@RequestMapping(value="/profile/UpdateUserProfileImage", method=RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<Void> createImage(@Valid @RequestParam MultipartFile image) throws NumberFormatException{
			if(loginService.isLoggedIn()) {
				HttpSession session = loginService.getSession();
				Users user = (Users) session.getAttribute("loggedInUser");
				if(user.getUserRoles() == UserRoles.ROLE_USER){
					byte[] img = null;
					try {
						img = image.getBytes();
					} catch (IOException e) {
						e.printStackTrace();
						return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
					}
					Profile userProf = profileService.findProfileByUserId(user.getUserID());
					userProf.setImage(img);
					userProf.setUserid(user);
					profileService.saveOrUpdate(userProf);
					return new ResponseEntity<Void>(HttpStatus.CREATED);
				}
				else
					return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
			else
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
}
