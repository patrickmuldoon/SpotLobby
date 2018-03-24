package com.patrick.SpotLobby.DAO.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.patrick.SpotLobby.SpotLobbyApplicationTests;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.DAO.UsersDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsersDAOTest extends SpotLobbyApplicationTests{

	@Autowired
	private UsersDAO usersDAO;
	
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
	
	@Ignore
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
	public void testAddFriend() {
		Users user = usersDAO.findById((long) 1).orElse(null);
		Users friend = usersDAO.findById((long) 2).orElse(null);
	}
	
	@Test
	@Transactional
	public void testFindUserFriends() {
		Users user = usersDAO.findById((long) 1).orElse(null);
		System.out.println(user.getFriends().size());
		//assertEquals(user.getFriends().size(), 1);
	}
	
}
