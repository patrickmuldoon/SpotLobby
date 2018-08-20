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
import com.patrick.SpotLobby.DAO.UsersDAO;
import com.patrick.SpotLobby.helpers.Password;

@Service
public class UsersServiceImpl implements UsersService {

	private UsersDAO usersDAO;
	
	@Autowired
	public UsersServiceImpl(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
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
		return usersDAO.getByEmail(email);
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


//	@Override
//	public void delete(String email) {
//		usersDAO.deleteByEmail(email);
//		
//	}

}
