package com.patrick.SpotLobby.DAOManagers;
import com.patrick.SpotLobby.Beans.Users;
import java.util.List;

public interface UsersDAOManager {

    public void create(Users user);
    public void update(Users user);
    public void delete(Users user);
    public Users findById(int userId);
    public Users findByEmail(String email);
    public Users findByUserName(String userName);
    public List<Users> findAll();
}
