package com.patrick.SpotLobby.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.patrick.SpotLobby.Beans.Profile;
import com.patrick.SpotLobby.DAO.ProfileDAO;

public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileDAO profileDAO;
	
	@Override
	public List<Profile> findAllProfiles() {
		return (List<Profile>) profileDAO.findAll();
	}

	@Override
	public Profile findProfileById(long id) {
		return profileDAO.findById(id).orElse(null);
	}

	@Override
	public Profile saveOrUpdate(Profile profile) {
		return profileDAO.save(profile);
	}

	@Override
	public void deleteProfile(long id) {
		profileDAO.deleteById(id);
	}

	@Override
	public Profile findProfileByUserId(long userid) {
		//return profileDAO.findProfileByUserId(userid);
		return null;
	}

}
