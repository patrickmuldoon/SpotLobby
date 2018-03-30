package com.patrick.SpotLobby.DAO.Tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class SpotLobbyTests {

	protected static AbstractApplicationContext context;
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Test
	public void testJdbcTemplate() {
		assertNotNull(jdbcTemplate);
	}
	
}
