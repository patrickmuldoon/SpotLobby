package com.patrick.SpotLobby.Beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PartyPrivacy implements Serializable {

	@JsonProperty("OPEN")
	PRIVACY_OPEN,
	@JsonProperty("FRIENDS_ONLY")
	PRIVACY_FRIENDS_ONLY,
	@JsonProperty("INVITE_ONLY")
	PRIVACY_INVITE_ONLY,
	@JsonProperty("CLOSED")
	PRIVACY_CLOSED
}
