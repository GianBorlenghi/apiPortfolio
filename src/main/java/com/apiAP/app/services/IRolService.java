package com.apiAP.app.services;

import java.util.List;
import java.util.Optional;

import com.apiAP.app.models.Rol;

public interface IRolService {

	public Rol findByNameRol(String name);
	public void saveRol(Rol rol);
	public void defaultRols();
}
