package com.apiAP.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiAP.app.models.TechProject;
import com.apiAP.app.services.ITechProjectServ;

@RestController
@RequestMapping("/teproj")
@CrossOrigin(origins = "https://hosting-angular-d2f7a.web.app/")
public class TechProjectController {

	@Autowired
	private ITechProjectServ tproServ;
	
	@PostMapping("admin/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveTechProj(@Valid @RequestBody TechProject tpro) {
		tproServ.saveTechProj(tpro);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
