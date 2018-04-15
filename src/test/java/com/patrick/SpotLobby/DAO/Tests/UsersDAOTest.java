package com.patrick.SpotLobby.DAO.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.patrick.SpotLobby.SpotLobbyApplicationTests;
import com.patrick.SpotLobby.Beans.Followers;
import com.patrick.SpotLobby.Beans.PartyPrivacy;
import com.patrick.SpotLobby.Beans.Profile;
import com.patrick.SpotLobby.Beans.Settings;
import com.patrick.SpotLobby.Beans.UserRoles;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.DAO.FollowersDAO;
import com.patrick.SpotLobby.DAO.ProfileDAO;
import com.patrick.SpotLobby.DAO.UsersDAO;
import com.patrick.SpotLobby.Services.UsersService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsersDAOTest extends SpotLobbyTests{

	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private FollowersDAO followersDAO;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ProfileDAO profileDAO;
	
//	@Autowired
//	private FollowingService followingService;
	
	private static final String FOLLOWERS_COUNT = "select count(id) from FOLLOWERS";

	private static final String PROFILE_STRING = "Hi my name is Patrick and I am a java developer. My music tastes "
			+ "include pop-punk, metal and 80's rock. I also get down with EDM and hip hop from time to time. Ask me for"
			+ "a follow :)";
	
	private static final String PROFILE_TEST_STRING = "Hi my name is Patrick";
	
	@Ignore
	@Test
	public void testSaveUser() {
		Users user = new Users();
		user.setFirstName("patrick");
		user.setLastName("muldoon");
		user.setEmail("patrick@example.com" );
		user.setUsername("muldoon20");
		user.setPassword("password");
		Users user2 = new Users("evan", "molinelli", "cunt", "passkey", "evan@example.com");
		usersDAO.save(user);
		usersDAO.save(user2);
	}
	
	@Ignore
	@Test
	public void testFindByEmail() throws SQLException {
		Users user = new Users();
		user.setUserID(new Integer(1));
		user.setFirstName("patrick");
		user.setLastName("muldoon");
		user.setEmail("patrick@example.com" );
		user.setUsername("muldoon20");
		user.setPassword("password");
		Users queryUser = usersDAO.getByEmail("patrick@example.com");
		//assertEquals(user, queryUser);
		assertEquals(user.getUserID(), queryUser.getUserID());
		assertEquals(user.getFirstName(), queryUser.getFirstName());
		assertEquals(user.getLastName(), queryUser.getLastName());
		assertEquals(user.getEmail(), queryUser.getEmail());
		assertEquals(user.getUsername(), queryUser.getUsername());
		assertEquals(user.getPassword(), queryUser.getPassword());
	}
	
	
	@Test
	public void testFindByID() {
		Users user = new Users();
		user.setUserID(new Integer(1));
		user.setFirstName("patrick");
		user.setLastName("muldoon");
		user.setEmail("patrick@example.com" );
		user.setUsername("muldoon20");
		user.setPassword("password");
		Users queryUser = usersDAO.findById((long) 1).orElse(null);
		assertEquals(user.getUserID(), queryUser.getUserID());
		assertEquals(user.getFirstName(), queryUser.getFirstName());
		assertEquals(user.getLastName(), queryUser.getLastName());
		assertEquals(user.getEmail(), queryUser.getEmail());
		assertEquals(user.getUsername(), queryUser.getUsername());
		assertEquals(user.getPassword(), queryUser.getPassword());
	}

	@Test
	public void testFindUserByUsername() {
		Users user = new Users("patrick", "muldoon", "muldoon20", "password", "patrick@example.com");
		user.setUserID(new Integer(1));
		Users queryUser = usersDAO.findByUsername("muldoon20");
		assertEquals(user.getUserID(), queryUser.getUserID());
		assertEquals(user.getFirstName(), queryUser.getFirstName());
		assertEquals(user.getLastName(), queryUser.getLastName());
		assertEquals(user.getEmail(), queryUser.getEmail());
		assertEquals(user.getUsername(), queryUser.getUsername());
		assertEquals(user.getPassword(), queryUser.getPassword());
	}
	
	@Ignore
	@Test
	public void testDeleteUserById() {
		Users user = new Users("patrick", "muldoon", "pmuldoon10", "passkey", "pmuldoon@example.com");
		usersDAO.save(user);
		Long id = usersDAO.getByEmail(user.getEmail()).getUserID();
		usersDAO.deleteById(id);
		assertNull(usersDAO.findById(id).orElse(null));
	}
	
	@Ignore
	@Test
	public void testFindAllUsers() {
		List<Users> usersList = (List<Users>) usersDAO.findAll();
		List<Users> expectedUsersList = new ArrayList<Users>();
		Users user = new Users("patrick", "muldoon", "muldoon20", "password", "patrick@example.com");
		user.setUserID(new Integer(1));
		Users user2 = new Users("evan", "cunt", "IamACunt", "carsonsucknutz", "evan@example.com");
		user2.setUserID(new Integer(2));
		expectedUsersList.add(user);
		expectedUsersList.add(user2);
		assertEquals(usersList.size(), expectedUsersList.size());
		assertEquals(usersList.get(0).getUsername(), expectedUsersList.get(0).getUsername());
	}
	
	@Ignore
	@Test
	public void testSaveAFollower() {
		Users user = usersDAO.findById((long) 1).orElse(null);
		Users friend = usersDAO.findById((long) 2).orElse(null);
		//Followers follower = new Followers(friend, user);
		//user.getFollowers().add(follower);
		//usersDAO.save(user);
//		assertEquals(user.getFollowers().size(), 1);
//		//user.getFollowers().remove(follower);
//		if(user.getFollowers().isEmpty())
//			System.out.println("followers empty");
//		usersDAO.save(user);
//		assertEquals(user.getFollowers().size(), 0);
	}
	
	@Ignore
	@Test
	public void testFollowerCount() {
		Users user = usersDAO.findById((long)1).orElse(null);
		List<Followers> followers = followersDAO.findAllFollowersByUserID(user.getUserID());
//		user.getFollowers().addAll(followers);
//		assertEquals(user.getFollowers().size(), 1);
	}
	
	@Ignore
	@Test
	public void removeFollower() {
		Followers follower = followersDAO.findFollowerByUserIDAndFollowerID(usersDAO.findById((long)1).orElse(null).getUserID(),
				usersDAO.findById((long)2).orElse(null).getUserID());
		System.out.println(follower.getId());
		followersDAO.deleteById(follower.getId());
		followersDAO.delete(follower);
		assertNull(followersDAO.findFollowerByUserIDAndFollowerID(usersDAO.findById((long)1).orElse(null).getUserID(),
				usersDAO.findById((long)2).orElse(null).getUserID()));
	}
	
	@Ignore
	@Test
	public void testCreateUser() {
		Users user = new Users("ciaran", "slattery", "brooklyn", "passpass", "ciaran@example.com");
		usersService.saveOrUpdate(user);
		List<Users> users = (List<Users>) usersDAO.findAll();
		assertEquals(users.size(), 5);
	}
	
	@Ignore
	@Test
	public void testUserFollowingAnotherUser() {
		Users follower = usersService.getById((long)3);
		Users following = usersService.getById((long)2);
		Followers newFollower = new Followers(follower, following);
		Long beforeCount = jdbcTemplate.queryForObject(FOLLOWERS_COUNT, Long.class);
		following.getFollowingUsers().add(newFollower);
		usersService.saveOrUpdate(following);
		Long afterCount = jdbcTemplate.queryForObject(FOLLOWERS_COUNT, Long.class);
		assertEquals(++beforeCount, afterCount);
		follower.setFollowers(usersDAO.findAllFollowersByUserID(follower.getUserID()));
		following.setFollowingUsers(usersDAO.findAllFollowingByUserID(following.getUserID()));
		assertEquals(follower.getFollowers().size(), 1);
		assertEquals(following.getFollowingUsers().size(), 2);
	}
	
	@Ignore
	@Test
	public void testFindUserFollowers() {
		Users user = usersService.getById((long)3);
		user.setFollowers(usersDAO.findAllFollowersByUserID(user.getUserID()));
		assertEquals(user.getFollowers().size(), 1);
	}
	
	@Ignore
	@Test 
	public void testFindUserFollowing() {
		Users user = usersService.getById((long)3);
		user.setFollowingUsers(usersDAO.findAllFollowingByUserID(user.getUserID()));
		assertEquals(user.getFollowingUsers().size(), 2);
	}
	
	@Ignore
	@Test 
	public void testAddUserRole() {
		Users user = usersService.getById((long)2);
		user.setUserRoles(UserRoles.ROLE_USER);
		usersService.saveOrUpdate(user);
		assertEquals(usersService.getById((long)2).getUserRoles(), UserRoles.ROLE_USER);
	}
	
	@Ignore
	@Test
	public void testCheckUserRole() {
		assertEquals(usersService.getById((long)1).getUserRoles(), UserRoles.ROLE_ADMIN);
		assertEquals(usersService.getById((long)2).getUserRoles(), UserRoles.ROLE_USER);
	}
	
	/**
	 * Changing implementation so that settings belong only to a lobby object
	 * but a user will have access to that lobby settings because they will 
	 * have a lobby object they are linked to
	 */
	@Ignore
	@Test
	public void testCreateNewUserWithSettings() {
		Users user = new Users("noel", "smith", "noelR'US", "passkeep", "noel@dk.com");
		user.setUserRoles(UserRoles.ROLE_USER);
		//user.setUserSettings(new Settings((long)1, "none", false, PartyPrivacy.PRIVACY_CLOSED, false));
		usersService.saveOrUpdate(user);
		//assertEquals(usersService.getById(user.getUserID()).getUserSettings().getPartyPrivacy(), PartyPrivacy.PRIVACY_CLOSED);
	}
	
	@Ignore
	@Test
	public void testAddUserProfile() {
		Users user = usersService.getById((long)1);
		user.getUserProfile().setBio(PROFILE_STRING);
		usersService.saveOrUpdate(user);
		//profileDAO.save(user.getUserProfile());
		assertEquals(user.getUserProfile().getBio(), PROFILE_STRING);
	}
	
}
