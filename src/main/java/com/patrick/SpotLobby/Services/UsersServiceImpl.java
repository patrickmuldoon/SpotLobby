package com.patrick.SpotLobby.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.patrick.SpotLobby.Beans.Followers;
import com.patrick.SpotLobby.Beans.Profile;
import com.patrick.SpotLobby.Beans.UserRoles;
import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.DAO.ProfileDAO;
import com.patrick.SpotLobby.DAO.UsersDAO;
import com.patrick.SpotLobby.helpers.Password;

@Service
public class UsersServiceImpl implements UsersService {

	private UsersDAO usersDAO;
	
	private ProfileDAO profileDAO;
	
	private ProfileService profileSerivce;
	
	@Autowired
	public UsersServiceImpl(UsersDAO usersDAO, ProfileDAO profileDAO, ProfileService profileService) {
		this.usersDAO = usersDAO;
		this.profileDAO = profileDAO;
		this.profileSerivce = profileService;
	}

	@Override
	public List<Users> listAll() {
		List<Users> users = new ArrayList<Users>();
		usersDAO.findAll().forEach(users::add);
		List<Users> result = new ArrayList<Users>();
		int i = 0;
		for(Users list : users) {
			result.add(new Users(list.getUserID(), list.getFirstName(), list.getLastName(),
					list.getUsername(), Collections.emptyList(), Collections.emptyList()));
			//get all followers put them in User Objects and add them to the users followers list
			List<Followers> temp = usersDAO.findAllFollowersByUserID(list.getUserID());
			List<Followers> followers = new ArrayList<Followers>();
			for(Followers fol : temp) {
				Followers follower = new Followers();
				Users user = new Users();
				user.setUsername(fol.getFollowing().getUsername());
				user.setFirstName(fol.getFollowing().getFirstName());
				user.setLastName(fol.getFollowing().getLastName());
				follower.setFollowers(user);
				followers.add(follower);
			}
			//get all following put them in User Objects and add the to the following lists
			List<Followers> temp2 = usersDAO.findAllFollowingByUserID(list.getUserID());
			List<Followers> following = new ArrayList<Followers>();
			for(Followers fol : temp2) {
				Followers followed = new Followers();
				Users user = new Users();
				user.setUsername(fol.getFollowers().getUsername());
				user.setFirstName(fol.getFollowers().getFirstName());
				user.setLastName(fol.getFollowers().getLastName());
				followed.setFollowing(user);
				following.add(followed);
			}
			result.get(i).setFollowers(followers);
			result.get(i).setFollowingUsers(following);
			i++;
		}
		return result;
	}

	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public Users getById(long id) {
		return usersDAO.findById(id).orElse(null);
	}

	@Override
	public Users saveOrUpdate(Users user) {
		user.setPassword(Password.hashPassword(user.getPassword()));
		user.setUserRoles(UserRoles.ROLE_USER);
		usersDAO.save(user);
		return user;
	}

	@Override
	public Users getByEmail(String email) {
		return usersDAO.findByEmail(email);
	}

	@Override
	public void delete(long id) {
		usersDAO.deleteById(id);
		
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
	rollbackFor=Exception.class)
	public Users getByUsername(String username) {
		return usersDAO.findByUsername(username);
	}

	@Override
	public void createUserProfile(Users user) {
		Profile userProf = new Profile();
		userProf.setUserid(user);
		user.setUserProfile(userProf);
		profileDAO.save(userProf);
	}

	@Override
	public List<Followers> getFollowersByUserID(long userid) {
		List<Followers> temp = usersDAO.findAllFollowersByUserID(userid);
		List<Followers> followers = new ArrayList<Followers>();
		for(Followers fol : temp) {
			Followers follower = new Followers();
			Users user = new Users();
			user.setUsername(fol.getFollowing().getUsername());
			user.setFirstName(fol.getFollowing().getFirstName());
			user.setLastName(fol.getFollowing().getLastName());
			follower.setFollowers(user);
			followers.add(follower);
		}
		return followers;
	}

	@Override
	public List<Followers> getFollowingByUserID(long userid) {
		List<Followers> temp2 = usersDAO.findAllFollowingByUserID(userid);
		List<Followers> following = new ArrayList<Followers>();
		for(Followers fol : temp2) {
			Followers followed = new Followers();
			Users user = new Users();
			user.setUsername(fol.getFollowers().getUsername());
			user.setFirstName(fol.getFollowers().getFirstName());
			user.setLastName(fol.getFollowers().getLastName());
			followed.setFollowing(user);
			following.add(followed);
		}
		return following;
	}

	@Override
	public List<Users> searchForUsers(String username) {
		List<Users> usersList = usersDAO.searchForUsers(username);
		if(!usersList.isEmpty()) {
			List<Users> result = new ArrayList<Users>();
			for(Users user : usersList) {
				Users validUser = new Users();
				validUser.setUsername(user.getUsername());
				result.add(validUser);
			}
			return result;
		}
		else
			return Collections.emptyList();
	}

	public Users setValidUserInformation(Users user, Users validUser) {
		validUser.setUsername(user.getUsername());
		validUser.setFirstName(user.getFirstName());
		validUser.setLastName(user.getLastName());
		validUser.setFollowers(getFollowersByUserID(user.getUserID()));
		validUser.setFollowingUsers(getFollowingByUserID(user.getUserID()));
		validUser.setUserProfile(profileSerivce.findProfileByUserId((long)user.getUserID()));
		return validUser;
	}
//	@Override
//	public void delete(String email) {
//		usersDAO.deleteByEmail(email);
//		
//	}

}
