package com.patrick.SpotLobby.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patrick.SpotLobby.Beans.Users;

@Service
public interface UsersService {
	
	List<Users> listAll();

	Users getById(long id);
	
	Users getByEmail(String email);
	
	Users getByUsername(String username);
	
	Users saveOrUpdate(Users user);
	
	void delete(long id);
	
//	void delete(String email);
}
