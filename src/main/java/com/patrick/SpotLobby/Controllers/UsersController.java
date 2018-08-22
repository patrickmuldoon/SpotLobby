package com.patrick.SpotLobby.Controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.patrick.SpotLobby.Beans.UserRoles;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.Services.InputValidationService;
import com.patrick.SpotLobby.Services.UsersService;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private InputValidationService inputValidationService;

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	public void setInputValidationService(InputValidationService inputValidationService) {
		this.inputValidationService = inputValidationService;
	}

	//must implement a validation service still for creating users
	@RequestMapping(value="/users/createNewUser", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Users> createUser(@Valid @RequestBody Users user){
		Users validUser = inputValidationService.validateInput(user);
		if(inputValidationService.isSignupInputValidated()) {
			user.setUserRoles(UserRoles.ROLE_USER);
			usersService.createUserProfile(user);
			usersService.saveOrUpdate(user);
			return new ResponseEntity<Users>(validUser, HttpStatus.CREATED);
		}else
			return new ResponseEntity<Users>(validUser, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/users/findById/{userId}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Users> findUserById(@PathVariable int userId){
		//logger.info("Finding User by Id number : " + userId);
		Users user = usersService.getById((long)userId);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/users/findAll", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<List<Users>> findAllUsers(){
		List<Users> users = new ArrayList<Users>();
		users = usersService.listAll();
		return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	}
	
}
