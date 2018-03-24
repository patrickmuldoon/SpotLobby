package com.patrick.SpotLobby.DAO;

import com.patrick.SpotLobby.Beans.Users;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends CrudRepository<Users, Long> {
	
	Users getByEmail(String email);
	
	Users findByUsername(String username);
	
//	@Query("Select from FRIENDS where userid = ?1")
//	Set<Users> findAllFriends(long id);
	
//	void deleteByEmail(String email);
}
