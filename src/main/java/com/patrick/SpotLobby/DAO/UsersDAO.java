package com.patrick.SpotLobby.DAO;

import com.patrick.SpotLobby.Beans.Users;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends CrudRepository<Users, Long> {
	Users getByEmail(String email);
	
	Users findByUsername(String username);
	
//	void deleteByEmail(String email);
}
