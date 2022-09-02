package com.apiAP.app.controllers;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apiAP.app.models.Education;
import com.apiAP.app.models.Image;
import com.apiAP.app.models.Person;
import com.apiAP.app.models.Project;
import com.apiAP.app.models.Technology;
import com.apiAP.app.models.WorkExperience;
import com.apiAP.app.services.IImageService;
import com.apiAP.app.services.IPersonService;
import com.apiAP.exceptions.RequestException;
import com.apiAP.exceptions.UserNotFoundException;


@RestController
@RequestMapping(value = "person")
@CrossOrigin(origins = /*"https://hosting-angular-d2f7a.web.app/"*/"*") 
public class PersonController {
	
	@Autowired
	private IPersonService serPer;
	
	@Autowired	
	private IImageService imgServ;
	
	@PostMapping("admin/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@Valid @RequestParam (value="file") MultipartFile file,
								@RequestParam(value="dateOfBirth") String dateOfBirth,
								@RequestParam (value="name") String name,
								@RequestParam (value="surname") String surname,
								@RequestParam (value="description") String description,
								@RequestParam (value="city") String city,
								@RequestParam (value="country") String country
								) throws ParseException, IOException {
			
		String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
	    Image FileDB = new Image(fileName, file.getContentType(), file.getBytes());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    Date birth = format.parse(dateOfBirth);
	    
	    Person per = new Person();
	    per.setDateOfBirth(birth);
	    per.setName(name);
	    per.setSurname(surname);
	    per.setDescription(description);
	    per.setCity(city);
	    per.setCountry(country);
	    per.setImg(FileDB);
	    
		serPer.savePerson(per);
		return new ResponseEntity<>("Person added",HttpStatus.OK);
		
		
	}
	
	@GetMapping("/getAllPerson")
	@ResponseBody
	public List<Person> getAllPerson() {
		return serPer.getAllPerson();
	}
	
	@PostMapping("/admin/edit/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> edit(@Valid @PathVariable (value = "id") Long idPerson,
					@RequestParam(value = "file") MultipartFile file,
					@RequestParam("dateOfBirth") String dateOfBirth,
					@RequestParam("name") String name,
					@RequestParam("surname") String surname,
					@RequestParam("description") String description,
					@RequestParam("city") String city,
					@RequestParam("country") String country) throws ParseException, IOException {
		
		Person per = serPer.findByID(idPerson);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date birth = format.parse(dateOfBirth);
		
		imgServ.findById(per.getImg().getId());
		
		String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
	    Image FileDB = new Image(fileName, file.getContentType(), file.getBytes());
		
		per.setName(name);
		per.setSurname(surname);
		per.setDateOfBirth(birth);
		per.setCity(city);
		per.setDescription(description);
		per.setCountry(country);
		per.setImg(FileDB);
		serPer.savePerson(per);
		return new ResponseEntity<>("Person edited",HttpStatus.OK);	
	}
	
	@PostMapping("/admin/editKeepImg/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> editKeepImg(@Valid @PathVariable (value = "id") Long idPerson,
					@RequestParam("dateOfBirth") String dateOfBirth,
					@RequestParam("name") String name,
					@RequestParam("surname") String surname,
					@RequestParam("description") String description,
					@RequestParam("city") String city,
					@RequestParam("country") String country) throws ParseException, IOException {
		
		Person per = serPer.findByID(idPerson);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date birth = format.parse(dateOfBirth);
		
		per.setName(name);
		per.setSurname(surname);
		per.setDateOfBirth(birth);
		per.setCity(city);
		per.setDescription(description);
		per.setCountry(country);
		serPer.savePerson(per);
		return new ResponseEntity<>("Person edited",HttpStatus.OK);	
	
	}
	
	@GetMapping("/getInfo/{id}")
	@ResponseBody
	public Person getInfo(@PathVariable (value = "id") Long idPerson) {	
		return serPer.findByID(idPerson);
	}
	
	@GetMapping("/getEducation/{id}")
	@ResponseBody
	public List<Education> getAllEducations(@PathVariable (value="id") Long idPerson) {
		List<Education> listEducation = serPer.viewAllEducations(idPerson);
		if(!listEducation.isEmpty()) {
			return listEducation;
		}else {
			throw new RequestException("Person "+idPerson+" doesn't have any education ", HttpStatus.NOT_FOUND, "p-404");

		}
	}
	
	@GetMapping("/getProject/{id}")
	@ResponseBody
	public List<Project> getAllProjects(@PathVariable(value = "id") Long idPerson){
		List<Project>listProject = serPer.viewAllProjects(idPerson);
		if(!listProject.isEmpty()) {
			return listProject;
		}else {
			throw new RequestException("Person "+idPerson+" doesn't have any associated project ", HttpStatus.NOT_FOUND, "p-404");
		}
	}
	
	@GetMapping("/getWork/{id}")
	@ResponseBody
	public List<WorkExperience> getAllWorks(@PathVariable(value = "id") Long idPerson){
		List<WorkExperience> listWorkExperience = serPer.viewAllWork(idPerson);
		if(!listWorkExperience.isEmpty()) {
			return listWorkExperience;
		}else {
			throw new RequestException("Person "+idPerson+" doesn't have any work experience ", HttpStatus.NOT_FOUND, "p-404");

		}
	}
	
	@GetMapping("/getTech/{id}")
	@ResponseBody
	public List<Technology> getAllTechnologys(@PathVariable(value = "id") Long idPerson){
		List<Technology>listTech = serPer.viewAllTechnology(idPerson);
		if(!listTech.isEmpty()) {
			return listTech;
		}else {
			throw new RequestException("Person "+idPerson+" doesn't have any asociated technology ", HttpStatus.NOT_FOUND, "p-404");

		}
	}
	
	@DeleteMapping("/admin/deletePerson/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deletePerson(@Valid @PathVariable (value = "id") Long idPerson){
		
		try {
			serPer.deletePerson(idPerson);
			return new ResponseEntity<>("Person with id "+idPerson+" successfully deleted.", HttpStatus.OK);
		}catch(EmptyResultDataAccessException e) {
			throw new UserNotFoundException("Person with id "+idPerson+" not exists", "p-404", HttpStatus.BAD_REQUEST);
		}
		
	}

}
