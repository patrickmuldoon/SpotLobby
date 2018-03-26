package com.patrick.SpotLobby.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.patrick.SpotLobby.Beans.Following;

public interface FollowingDAO extends CrudRepository<Following, Long> {

	@Query("from Following where followed_id =:userID")
	List<Following> findAllFollowersByUserID(@Param("userID")long userID);
	
	@Query("from Followers where followed_id =:userID and following_id =:followerID")
	Following findFollowingByUserIDAndFollowingID(@Param("userID") long userID, @Param("followerID")long followerID);
}
