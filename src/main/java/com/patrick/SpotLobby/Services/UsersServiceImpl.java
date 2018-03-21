package com.patrick.SpotLobby.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patrick.SpotLobby.Beans.Users;
import com.patrick.SpotLobby.DAO.UsersDAO;

@Service
public class UsersServiceImpl implements UsersService {

	private UsersDAO usersDAO;
	
	@Autowired
	public UsersServiceImpl(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	@Override
	public List<Users> listAll() {
		List<Users> users = new ArrayList<>();
		usersDAO.findAll().forEach(users::add);
		return users;
	}

	@Override
	public Users getById(long id) {
		return usersDAO.findById(id).orElse(null);
	}

	@Override
	public Users saveOrUpdate(Users user) {
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

//	@Override
//	public void delete(String email) {
//		usersDAO.deleteByEmail(email);
//		
//	}

}
