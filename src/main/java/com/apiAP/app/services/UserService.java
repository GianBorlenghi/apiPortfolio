package com.apiAP.app.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.Rol;
import com.apiAP.app.models.User;
import com.apiAP.app.repositories.IUserRepo;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepo userRep;

	@Autowired
	private IRolService rolServ;
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

	@Override
	public void addUser() {
		
	}

	@Override
	public void administratorDefault(User administrator) {
		Rol rol = rolServ.findByNameRol("ROLE_ADMIN");
		administrator.setRoles(Collections.singleton(rol));
		userRep.save(administrator);
	}
	
		@Override
	public void deleteUser(Long id) {

		userRep.deleteById(id);
	}


	
}
