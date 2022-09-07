package com.apiAP.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apiAP.app.models.Education;
import com.apiAP.app.services.IEducationService;
import com.apiAP.app.services.IPersonService;
import com.apiAP.exceptions.RequestException;

@RestController
@RequestMapping("education")
@CrossOrigin(origins = /*"https://hosting-angular-d2f7a.web.app/"*/"*")
public class EducationController {

	@Autowired
	private IEducationService eduService;
	@Autowired
	private IPersonService personServ;
	
	@PostMapping("admin/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveEdu(@Valid @RequestBody Education edu) {
		eduService.saveEducation(edu);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("admin/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteEdu(@Valid @PathVariable (value = "id") Long idEducation) {
			eduService.deleteEducation(idEducation);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("getEducationInfo/{id}")
	public Education getEducationInfo(@PathVariable (value="id") Long idEducation) {
		try{
			return eduService.findById(idEducation);
		}catch(NoSuchElementException e) {
			throw new RequestException("Education with id: "+idEducation+" not found.", HttpStatus.NOT_FOUND, "404");
		}
	}
	
	@PutMapping("admin/editEducation/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> editEduc(@PathVariable(value = "id") Long idEducation,
									  @RequestParam(value="name") String name,
									  @RequestParam(value="area") String area,
									  @RequestParam(value="institution") String institution,
									  @RequestParam(value="date_of_graduation") String dateOfGraduation,
									  @RequestParam(value="hours") String hours,
									  @RequestParam(value="idPerson") String idPerson) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date graduation = format.parse(dateOfGraduation);
		
		Education educ = eduService.findById(idEducation);
		
		educ.setName(name);
		educ.setArea(area);
		educ.setDateOfGraduation(graduation);
		educ.setInstitution(institution);
		educ.setEducPerson(personServ.findByID(Long.parseLong(idPerson)));
		educ.setHours(Integer.parseInt(hours));
		eduService.saveEducation(educ);
		
		return new ResponseEntity<>("Education with id "+idEducation+" edited.", HttpStatus.OK);
	
	}
}
