package com.patrick.SpotLobby.Beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public enum UserRoles implements Serializable{
	@JsonProperty("ROLE_ADMIN")
	ROLE_ADMIN,
	@JsonProperty("ROLE_USER")
	ROLE_USER,
	@JsonProperty("ROLE_INACTIVE")
	ROLE_INACTIVE
	
}
