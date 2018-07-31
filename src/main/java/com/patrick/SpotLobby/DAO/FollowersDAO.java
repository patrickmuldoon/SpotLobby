package com.patrick.SpotLobby.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patrick.SpotLobby.Beans.Followers;

@Repository
public interface FollowersDAO extends CrudRepository<Followers, Long> {

	@Query("from Followers where following_id =:userID")
	List<Followers> findAllFollowersByUserID(@Param("userID")long userID);
	
	@Query("from Followers where follower_id = :userID")
	List<Followers> findAllFollowingByUserID(@Param("userID")long userID);
	
	@Query("from Followers where following_id =:userID and follower_id =:followerID")
	Followers findFollowerByUserIDAndFollowerID(@Param("userID") long userID, @Param("followerID")long followerID);
}
