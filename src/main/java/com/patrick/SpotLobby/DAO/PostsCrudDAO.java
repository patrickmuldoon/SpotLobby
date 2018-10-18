package com.patrick.SpotLobby.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.patrick.SpotLobby.Beans.Posts;

@Repository
public interface PostsCrudDAO extends CrudRepository<Posts, Long>{
	
	@Transactional
	@Query("from Posts where userid = :user_ID")
	List<Posts> findPostsByUserID(@Param("user_ID") long userid);
	
}
