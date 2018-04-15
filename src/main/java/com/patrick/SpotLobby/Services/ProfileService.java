package com.patrick.SpotLobby.Services;

import java.util.List;

import com.patrick.SpotLobby.Beans.Profile;

public interface ProfileService {

	List<Profile> findAllProfiles();
	
	Profile findProfileById(long id);
	
	Profile findProfileByUserId(long userid);
	
	Profile saveOrUpdate(Profile profile);
	
	void deleteProfile(long id);
	
}
