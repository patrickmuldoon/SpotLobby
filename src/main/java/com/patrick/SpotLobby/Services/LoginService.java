package com.patrick.SpotLobby.Services;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.helpers.Password;

@Service
public class LoginService {
	
private UsersService userService;
	
	private boolean isLoggedIn = false;
	private HttpSession session;
	
	@Autowired
	public void setUsersService(UsersService userService) {
		this.userService = userService;
	}
	

	public Users authenticate(Users user, HttpSession session){
		if (user.getUsername() != null) {
			String cleanUsername = Jsoup.clean(user.getUsername(), Whitelist.basic());
			String cleanPassword = Jsoup.clean(user.getPassword(), Whitelist.basic());
			if (userService.getByUsername(cleanUsername) != null &&
					cleanUsername.equals(userService.getByUsername(cleanUsername).getUsername())) {
				Users validUser = userService.getByUsername(cleanUsername);
				if (Password.checkPassword(cleanPassword, validUser.getPassword())) {
					isLoggedIn = true;
					Users sessionUser = new Users(validUser.getUserID(), validUser.getFirstName(),
							validUser.getLastName(), validUser.getUsername());
					sessionUser.setFollowers(userService.getFollowersByUserID(sessionUser.getUserID()));
					sessionUser.setFollowingUsers(userService.getFollowingByUserID(sessionUser.getUserID()));
					sessionUser.setEmail(validUser.getEmail());
					sessionUser.setUserRoles(validUser.getUserRoles());
					session.setAttribute("loggedInUser", sessionUser);
					this.session = session;
					return sessionUser;
				}else
					return new Users();
			}else
				return new Users();
		} else 
			return new Users();
	}
	
	public void logout(HttpSession session){
		session.invalidate();
		isLoggedIn = false;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	
}