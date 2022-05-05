package com.apiAP.app.services;

import java.util.List;

import com.apiAP.app.models.User;

public interface IUserService {

	public void saveUser(User user);
	public User findByUsername(String username);
	public boolean emailExists(String mail);
	public List<User> listUser();
}
