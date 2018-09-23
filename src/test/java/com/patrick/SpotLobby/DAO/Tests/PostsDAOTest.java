package com.patrick.SpotLobby.DAO.Tests;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.patrick.SpotLobby.Beans.Posts;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.Services.PostsService;
import com.patrick.SpotLobby.Services.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostsDAOTest {
	
	private static final String POSTS_COUNT = "select count(id) from POSTS";
	
	private static final String USERS_POSTS_COUNT = "select count(id) from POSTS where userID = '1'";

	@Autowired
	private UsersService userService;
	
	@Autowired
	private PostsService postService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	//might need to check and make sure sensative data is not being passed here
	@Ignore
	@Test
	public void CreateMessageTest() {
		Users user = userService.getById((long)1);
		Posts post = new Posts("This is a new post", user, new Timestamp(System.currentTimeMillis()));
		long before = jdbcTemplate.queryForObject(POSTS_COUNT, Long.class);
		postService.saveOrUpdate(post);
		long after = jdbcTemplate.queryForObject(POSTS_COUNT, Long.class);
		assertEquals(++before, after);
	}
	
	@Test 
	public void findAllPostsTest() {
		long numberOfPosts = jdbcTemplate.queryForObject(POSTS_COUNT, Long.class);
		long numberOfPostsFound = postService.listAll().size();
		assertEquals(numberOfPosts, numberOfPostsFound);
	}
	
	@Test
	public void findAllPostsByUserIDTest() {
		List<Posts> posts = postService.findByUserID((long)1);
		long postCount = jdbcTemplate.queryForObject(USERS_POSTS_COUNT, Long.class);
		assertEquals(postCount, (long)posts.size());
	}
	
	@Test
	public void findPostByIDTest() {
		Posts post = postService.findById((long)1);
		assertEquals(post.getMessage(), "This is a new post");
	}
	
	@Ignore
	@Test
	public void deletePostByIDTest() {
		long before = jdbcTemplate.queryForObject(POSTS_COUNT, Long.class);
		postService.deleteByID((long)1);
		long after = jdbcTemplate.queryForObject(POSTS_COUNT, Long.class);
		assertEquals(before, ++after);
	}
	
	@Test 
	public void addUpvoteAndDownvoteToPostTest() {
		Posts post = postService.findById((long)1);
		long upvotes = post.getUpvotes();
		long downvotes = post.getDownvotes();
		upvotes++;
		downvotes++;
		post.setUpvotes(upvotes);
		post.setDownvotes(downvotes);
		Timestamp updateTime = new Timestamp(System.currentTimeMillis());
		post.setTimeMessageLastEdited(updateTime);
		postService.saveOrUpdate(post);
		Posts updatedPost = postService.findById((long)1);
		assertEquals(upvotes, updatedPost.getUpvotes());
		assertEquals(downvotes, updatedPost.getDownvotes());
		assertEquals(updateTime, updatedPost.getTimeMessageLastEdited());
	}
	
	@Test
	public void removeUpvoteAndDownvoteToPostTest() {
		Posts post = postService.findById((long)1);
		long upvotes = post.getUpvotes();
		long downvotes = post.getDownvotes();
		upvotes--;
		downvotes--;
		post.setUpvotes(upvotes);
		post.setDownvotes(downvotes);
		Timestamp updateTime = new Timestamp(System.currentTimeMillis());
		post.setTimeMessageLastEdited(updateTime);
		postService.saveOrUpdate(post);
		Posts updatedPost = postService.findById((long)1);
		assertEquals(upvotes, updatedPost.getUpvotes());
		assertEquals(downvotes, updatedPost.getDownvotes());
		assertEquals(updateTime, updatedPost.getTimeMessageLastEdited());
	}
}
