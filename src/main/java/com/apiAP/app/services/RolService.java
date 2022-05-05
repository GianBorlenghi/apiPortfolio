package com.apiAP.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.Rol;
import com.apiAP.app.repositories.IRolRepo;

@Service
public class RolService implements IRolService {

	@Autowired
	private IRolRepo rolRepo;
	@Override
	public Rol findByNameRol(String name) {
		List<Rol> listRols = rolRepo.findAll();
		
		for(Rol rol : listRols) {
			if(rol.getNombre().equals(name)) {
				return rol;
			}
		}
		return null;
		
	}
	@Override
	public void saveRol(Rol rol) {
		rolRepo.save(rol);
	}


}
