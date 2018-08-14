package com.patrick.SpotLobby.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.Services.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Users> login(@RequestBody Users user, HttpServletRequest req){
		Users validUser = loginService.authenticate(user, req.getSession());
		return loginService.isLoggedIn() == true ? new ResponseEntity<Users>(validUser, HttpStatus.OK) :
			new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ResponseEntity<Void> logout(HttpServletRequest req){
		HttpSession session = req.getSession();
		loginService.logout(session);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/isLoggedIn", method=RequestMethod.GET)
	public ResponseEntity<Users> isLoggedIn(HttpServletRequest req){
		HttpSession session = req.getSession();
		if(loginService.isLoggedIn()){
			return new ResponseEntity<Users>((Users)session.getAttribute("loggedInUser"), HttpStatus.OK);
		}else{
			return new ResponseEntity<Users>(new Users(), HttpStatus.UNAUTHORIZED);
		}
	}
}
