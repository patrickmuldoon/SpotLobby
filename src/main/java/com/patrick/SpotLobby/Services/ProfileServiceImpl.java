package com.patrick.SpotLobby.Services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.patrick.SpotLobby.Beans.Profile;
import com.patrick.SpotLobby.DAO.ProfileDAO;
import com.patrick.SpotLobby.DAO.UserProfileDAO;

@Service
public class ProfileServiceImpl implements ProfileService {

	
	private ProfileDAO profileDAO;
	
	private UserProfileDAO userProfileDAO;

	@Autowired
	public ProfileServiceImpl(ProfileDAO profileDAO, UserProfileDAO userProfileDAO) {
		this.profileDAO = profileDAO;
		this.userProfileDAO = userProfileDAO;
	}

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

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	@Override
	public Profile findProfileByUserId(long userid) {
		Profile userProf = new Profile();
		Profile dbProf = new Profile();
		dbProf = userProfileDAO.findByuserid(userid);
		if(dbProf != null) {
			userProf.setId(dbProf.getId());
			userProf.setBio(dbProf.getBio());
			userProf.setImage(dbProf.getImage());
		}
		return userProf;
	}

}
