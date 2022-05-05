package com.apiAP.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiAP.app.models.Project;
import com.apiAP.app.services.IProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private IProjectService projServ;
	
	@PostMapping("admin/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveProj(@RequestBody Project proj) {
		projServ.saveProject(proj);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
