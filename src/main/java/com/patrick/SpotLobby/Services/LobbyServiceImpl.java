package com.patrick.SpotLobby.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.patrick.SpotLobby.Beans.Lobby;
import com.patrick.SpotLobby.DAO.LobbyDAO;

public class LobbyServiceImpl implements LobbyService{

	@Autowired
	private LobbyDAO lobbyDAO;
	
	@Override
	public List<Lobby> listAll() {
		return (List<Lobby>) lobbyDAO.findAll();
	}

	@Override
	public Lobby findByLobbyId(long id) {
		return lobbyDAO.findById(id).orElse(null);
	}

	@Override
	public Lobby saveOrUpdate(Lobby lobby) {
		return lobbyDAO.save(lobby);
	}

	@Override
	public void deleteLobby(long id) {
		lobbyDAO.deleteById(id);
	}

}
