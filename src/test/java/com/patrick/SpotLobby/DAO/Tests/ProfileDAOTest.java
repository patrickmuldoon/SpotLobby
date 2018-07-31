package com.patrick.SpotLobby.DAO.Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.patrick.SpotLobby.Beans.Profile;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.DAO.ProfileDAO;
import com.patrick.SpotLobby.DAO.UsersDAO;
import com.patrick.SpotLobby.Services.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProfileDAOTest {

	private final static String PROFILE_BIO = "I am a fullstack web developer, coding mostly in Java."
			+ " I do enjoy a wide variety of music including rock, 80's and EDM. Follow me and share your "
			+ " musical tastes with me :)";
	
	private final static String BASIC_BIO = "I am a human being!";
	
	@Autowired
	private ProfileDAO profileDAO;
	
	@Autowired
	private UsersService usersService;
	
	//works
	@Ignore
	@Test
	public void testCreateNewUserProfileOnAlreadyExistingUser() {
		Users testUser = usersService.getById((long)1);
		testUser.setUserProfile(new Profile(testUser, PROFILE_BIO));
		usersService.saveOrUpdate(testUser);
		assertEquals(usersService.getById((long)1).getUserProfile().getBio(), PROFILE_BIO);
	}
	
	@Test 
	public void testFindingUserProfile() {
		Users testUser = usersService.getById((long)1);
		assertEquals(testUser.getUserProfile().getBio(), PROFILE_BIO);
	}
	
	//works
	@Ignore
	@Test
	public void testCreateNewUserProfileOnNewUser() {
		Users testUser = new Users("ciaran", "slattery", "eliIsABum", "passpass", "ciaran@example.com");
		usersService.saveOrUpdate(testUser);
		testUser.setUserProfile(new Profile(testUser, BASIC_BIO));
		usersService.saveOrUpdate(testUser);
		assertEquals(usersService.getByUsername("eliIsABum").getUserProfile().getBio(), BASIC_BIO);
	}
	
	
}
