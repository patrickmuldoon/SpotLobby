package com.patrick.SpotLobby.DAO;

import org.springframework.data.repository.CrudRepository;

import com.patrick.SpotLobby.Beans.Lobby;

public interface LobbyDAO extends CrudRepository<Lobby, Long> {

}
