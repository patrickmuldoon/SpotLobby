package com.patrick.SpotLobby.DAO.Tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.patrick.SpotLobby.Beans.Lobby;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.DAO.LobbyDAO;
import com.patrick.SpotLobby.DAO.UsersDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LobbyDAOTest extends SpotLobbyTests {

	
	private static final String LOBBY_COUNT = "select count(lobby_id) from LOBBY";
	
	@Autowired
	private LobbyDAO lobbyDAO;
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Ignore
	@Test
	public void createNewLobbyTest() {
		Users testUser = usersDAO.findById((long)1).orElse(null);
		Lobby testLobby = new Lobby();
		testLobby.setServerOn(false);
		testUser.setLobby(testLobby);
		long before = jdbcTemplate.queryForObject(LOBBY_COUNT, Long.class);
		usersDAO.save(testUser);
		long after = jdbcTemplate.queryForObject(LOBBY_COUNT, Long.class);
		assertEquals(++before, after);
	}
	
	@Test
	public void findAllLobbies() {
		List<Lobby> lobbyList = (List<Lobby>) lobbyDAO.findAll();
		long actual = jdbcTemplate.queryForObject(LOBBY_COUNT, Long.class);
		assertEquals(actual, (long)1);
	}
	
}
