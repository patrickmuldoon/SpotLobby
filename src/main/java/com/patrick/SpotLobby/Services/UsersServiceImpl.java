package com.patrick.SpotLobby.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
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
