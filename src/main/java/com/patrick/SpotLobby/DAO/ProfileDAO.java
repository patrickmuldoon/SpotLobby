package com.patrick.SpotLobby.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.patrick.SpotLobby.Beans.Profile;

@Repository
public interface ProfileDAO extends CrudRepository<Profile, Long>{

	@Query("from Profile where userid = :user_ID")
	Profile findProfileByUserId(@Param("user_ID") long userid);
	
}
