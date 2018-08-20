package com.patrick.SpotLobby.DAO;

import com.patrick.SpotLobby.Beans.Followers;
import com.patrick.SpotLobby.Beans.Users;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersDAO extends CrudRepository<Users, Long> {
	
	Users getByEmail(String email);
	
	Users findByUsername(String username);
	
	@Transactional
	@Query("from Followers where follower_ID = :userID")
	List<Followers> findAllFollowersByUserID(@Param("userID") long userid);
	
	@Transactional
	@Query("from Followers where following_ID = :userID")
	List<Followers> findAllFollowingByUserID(@Param("userID")long userid);

}
