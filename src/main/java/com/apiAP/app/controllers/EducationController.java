package com.apiAP.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiAP.app.models.Education;
import com.apiAP.app.services.IEducationService;

@RestController
@RequestMapping("education")
public class EducationController {

	@Autowired
	private IEducationService eduService;
	
	@PostMapping("admin/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveEdu(@RequestBody Education edu) {
		eduService.saveEducation(edu);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("admin/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteEdu(@PathVariable (value = "id") Long idEducation) {
			eduService.deleteEducation(idEducation);
			return new ResponseEntity<>(HttpStatus.OK);
	}
}
