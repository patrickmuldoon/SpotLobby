package com.patrick.SpotLobby.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patrick.SpotLobby.Beans.Profile;

@Service
public interface ProfileService {

	List<Profile> findAllProfiles();
	
	Profile findProfileById(long id);
	
	Profile findProfileByUserId(long userid);
	
	Profile saveOrUpdate(Profile profile);
	
	void deleteProfile(long id);
	
}
