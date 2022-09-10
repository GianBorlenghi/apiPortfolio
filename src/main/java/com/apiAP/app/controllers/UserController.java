package com.apiAP.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apiAP.app.services.IImageService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IImageService imgServ;
	
	@DeleteMapping("/deleteImg/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteImg(@PathVariable (value="id") Long idImg) {
		imgServ.delteImg(idImg);
		return "Deleted";
	}
	


}
