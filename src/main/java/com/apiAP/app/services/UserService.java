package com.apiAP.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.User;
import com.apiAP.app.repositories.IUserRepo;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepo userRep;

	@Override
	public void saveUser(User user) {
		userRep.save(user);
	}


	@Override
	public boolean emailExists(String mail) {
		List<User> list =  userRep.findAll();

		for(User us : list) {
			if(mail.equals(us.getMail())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<User> listUser() {
		return userRep.findAll();
	}
	
	public User findByUsername(String username) {
		List<User> users = userRep.findAll();
		
		for(User us : users) {
			if(us.getMail().equals(username)) {
				return us;
			}
		}
		
		return null;
	}

	
}
