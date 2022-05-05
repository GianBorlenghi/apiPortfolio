package com.apiAP.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiAP.app.models.Technology;
import com.apiAP.app.services.ITechnologyService;

@RestController
@RequestMapping(value = "technologys")
public class TechnologyController {
	
	@Autowired
	private ITechnologyService tecServ;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("admin/add")
	public ResponseEntity<?> addTec(@RequestBody Technology tec) {
		tecServ.saveTechnology(tec);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
