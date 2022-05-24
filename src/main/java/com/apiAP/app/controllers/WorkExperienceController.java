package com.apiAP.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apiAP.app.models.WorkExperience;
import com.apiAP.app.services.IWorkExperienceService;

@Controller
@RequestMapping("/workExperience")
public class WorkExperienceController {

	@Autowired
	private IWorkExperienceService workServ;
	
	@PostMapping("/admin/addWork")
	public ResponseEntity<?> addWork(@Valid @RequestBody WorkExperience workExp){
		
			workServ.createWork(workExp);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/listWorks")
	@ResponseBody
	public List<WorkExperience> getAllWorks(){
		return workServ.workList();
		
	}
}
