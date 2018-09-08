package com.patrick.SpotLobby.DAO.Tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.patrick.SpotLobby.Beans.Followers;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.DAO.FollowersCrudDAO;
import com.patrick.SpotLobby.DAO.FollowersDAO;
import com.patrick.SpotLobby.DAO.UsersDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FollowersDAOTest {
	
	private static final String FOLLOWER_COUNT = "select Count(id) from followers";

	@Autowired
	private FollowersDAO followersDAO;
	
	@Autowired
	private FollowersCrudDAO followersCrudDAO;
	
	@Autowired
	private UsersDAO userDAO;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setFollowersDAO(FollowersDAO followersDAO) {
		this.followersDAO = followersDAO;
	}
	
	public void setUserDAO(UsersDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setFollowersCrudDAO(FollowersCrudDAO followersCrudDAO) {
		this.followersCrudDAO = followersCrudDAO;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//works for creating follower as well as removing. Will need to set up H2 database to run tests in order
	@Ignore
	@Test
	public void unfollowUserTest() {
		Users user = userDAO.findById((long)302).orElse(null); 
		Users followedUser = userDAO.findById((long)202).orElse(null);
		Followers followerObject = followersCrudDAO.findFollowerByUserIDAndFollowerID(followedUser.getUserID(), user.getUserID());
		long rowsBefore = jdbcTemplate.queryForObject(FOLLOWER_COUNT, long.class);
		followersCrudDAO.delete(followerObject);
		long rowsAfter = jdbcTemplate.queryForObject(FOLLOWER_COUNT, long.class);
		assertEquals(rowsBefore, ++rowsAfter);
	}
	
	@Ignore
	@Test
	public void followUserTest() {
		Users user = userDAO.findById((long)302).orElse(null); 
		Users followedUser = userDAO.findById((long)202).orElse(null);
		Followers followerObject = new Followers();
		followerObject.setFollowers(user);
		followerObject.setFollowing(followedUser);
		long rowsBefore = jdbcTemplate.queryForObject(FOLLOWER_COUNT, long.class);
		followersCrudDAO.save(followerObject);
		long rowsAfter = jdbcTemplate.queryForObject(FOLLOWER_COUNT, long.class);
		assertEquals(++rowsBefore, rowsAfter);
	}
	
	@Test
	public void findAllFollowersTest() {
		Users user = userDAO.findById((long)3).orElse(null);
		List<Followers> followers = followersCrudDAO.findAllFollowingByUserID(user.getUserID());
		assertEquals(followers.size(), 2);
	}
	
	@Test
	public void findAllFollowingTest() {
		Users user = userDAO.findById((long)3).orElse(null);
		List<Followers> followers = followersCrudDAO.findAllFollowersByUserID(user.getUserID());
		assertEquals(followers.size(), 2);
	}
}
