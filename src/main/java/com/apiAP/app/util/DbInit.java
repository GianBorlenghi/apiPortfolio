package com.apiAP.app.util;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apiAP.app.models.Rol;
import com.apiAP.app.models.User;
import com.apiAP.app.services.IRolService;
import com.apiAP.app.services.IUserService;

@Component
public class DbInit {

	@Autowired
	private IUserService userServ;
	
	@Autowired
	private IRolService rolServ;
	
	@PostConstruct
	private void postConstruct() {
		Rol rol = rolServ.findByNameRol("ROLE_ADMIN");
		if(rol == null) {
			rolServ.defaultRols();
		}
		com.apiAP.app.models.User us = userServ.findByUsername("admin@admin.com");
		if(us==null) {
			User administrator = new User();
			administrator.setMail("admin@admin.com");
			administrator.setPass("$2a$10$vESJ2XmxUfc27MCdAFsXsu1xtoaIeFM2fp4dy1JonkOlHs2QCU.eW");
			userServ.saveUser(administrator);
			userServ.administratorDefault(administrator);
			
		}
		
	}
	
	
}
