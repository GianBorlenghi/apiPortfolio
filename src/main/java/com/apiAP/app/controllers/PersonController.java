package com.apiAP.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apiAP.app.models.Education;
import com.apiAP.app.models.Person;
import com.apiAP.app.models.Project;
import com.apiAP.app.models.Technology;
import com.apiAP.app.models.WorkExperience;
import com.apiAP.app.services.IPersonService;


@RestController
@RequestMapping(value = "person")
public class PersonController {
	
	@Autowired
	private IPersonService serPer;
		
	@PostMapping("admin/add")
	@PreAuthorize("hasRole('ADMIN')")
	public String save(@RequestBody Person per) {
		serPer.savePerson(per);
		return per.getName()+" ,agregado correctamente";
	}
	
	@PostMapping("admin/edit/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public void edit(@PathVariable (value = "id") Long idPerson,
					@RequestParam String dateOfBirth,
					@RequestParam String name,
					@RequestParam String surname,
					@RequestParam String description,
					@RequestParam String city) throws ParseException {
		
		Person per = serPer.findByID(idPerson);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date birth = format.parse(dateOfBirth);
		
		per.setName(name);
		per.setSurname(surname);
		per.setDateOfBirth(birth);
		per.setCity(city);
		per.setDescription(description);
		serPer.savePerson(per);
		
	}
	
	@GetMapping("/getEducation/{id}")
	@ResponseBody
	public List<Education> getAllEducations(@PathVariable (value="id") Long idPerson) {
		
		return serPer.viewAllEducations(idPerson);
	}
	
	@GetMapping("/getProject/{id}")
	@ResponseBody
	public List<Project> getAllProjects(@PathVariable(value = "id") Long idPerson){
		
		return serPer.viewAllProjects(idPerson);
	}
	
	@GetMapping("/getWork/{id}")
	@ResponseBody
	public List<WorkExperience> getAllWorks(@PathVariable(value = "id") Long idPerson){
		
		return serPer.viewAllWork(idPerson);
	}
	
	@GetMapping("/getTech/{id}")
	@ResponseBody
	public List<Technology> getAllTechnologys(@PathVariable(value = "id") Long idPerson){
		
		return serPer.viewAllTechnology(idPerson);
	}

}
