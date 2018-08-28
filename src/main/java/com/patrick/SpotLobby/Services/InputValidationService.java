package com.patrick.SpotLobby.Services;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.patrick.SpotLobby.Beans.Users;

@Service
public class InputValidationService {

	private boolean signupInputValidated = false;
	private static String userNameAndPasswordRegex = "[a-zA-Z0-9_.!?]{4,20}";
	private static String nameRegex = "[a-zA-Z0-9']{1,25}";
	private static String emailRegex = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

	@Autowired
	private UsersService userService;

	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	public Users validateInput(Users user){
		String cleanUsername = Jsoup.clean(user.getUsername(), Whitelist.basic());
		String cleanPassword = Jsoup.clean(user.getPassword(), Whitelist.basic());
		String cleanFirstName = Jsoup.clean(user.getFirstName(), Whitelist.basic());
		String cleanLastName = Jsoup.clean(user.getLastName(), Whitelist.basic());
		String cleanEmail = Jsoup.clean(user.getEmail(), Whitelist.basic());
		if(cleanUsername.length() < 4 || cleanUsername.length() > 20 || 
				!cleanUsername.matches(userNameAndPasswordRegex) || userService.getByUsername(cleanUsername) != null){
			return new Users();
		}else if(cleanPassword.length() < 8 || cleanPassword.length() > 20 ||
				!cleanPassword.matches(userNameAndPasswordRegex)){
			return new Users();
		}else if(cleanFirstName.length() < 1 || cleanFirstName.length() > 25 ||
				!cleanFirstName.matches(nameRegex)){
			return new Users();
		}else if(cleanLastName.length() < 1 || cleanLastName.length() > 25 ||
				!cleanLastName.matches(nameRegex)){
			return new Users();
		}else if(!cleanEmail.matches(emailRegex) || userService.getByEmail(cleanEmail) != null){
			return new Users();
		}else{
			signupInputValidated = true;
			return new Users(cleanFirstName, cleanLastName, cleanUsername, cleanPassword, cleanEmail);
		}
	}
	
	

	public boolean isSignupInputValidated() {
		return signupInputValidated;
	}

	public void setSignupInputValidated(boolean signupInputValidated) {
		this.signupInputValidated = signupInputValidated;
	}

}