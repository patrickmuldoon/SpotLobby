package com.patrick.SpotLobby.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patrick.SpotLobby.Beans.Lobby;

@Repository
public interface LobbyDAO extends CrudRepository<Lobby, Long> {

}
