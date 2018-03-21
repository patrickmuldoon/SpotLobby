package com.patrick.SpotLobby.DAO.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		usersDAO.save(user);
	}
	
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
	public void testDeleteUserById() {
		Users user = new Users("patrick", "muldoon", "pmuldoon10", "passkey", "pmuldoon@example.com");
		usersDAO.save(user);
		Long id = usersDAO.getByEmail(user.getEmail()).getUserID();
		usersDAO.deleteById(id);
		assertNull(usersDAO.findById(id).orElse(null));
	}
	
}
