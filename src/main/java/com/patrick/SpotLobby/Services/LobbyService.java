package com.patrick.SpotLobby.Services;

import java.util.List;

import com.patrick.SpotLobby.Beans.Lobby;

public interface LobbyService {

	List<Lobby> listAll();
	
	Lobby findByLobbyId(long id);
	
	Lobby saveOrUpdate(Lobby lobby);
	
	void deleteLobby(long id);
}
