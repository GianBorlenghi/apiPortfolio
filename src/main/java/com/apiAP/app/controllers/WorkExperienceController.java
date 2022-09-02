package com.apiAP.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.apache.tomcat.util.http.parser.MediaType;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartFile;

import com.apiAP.app.models.Image;
import com.apiAP.app.models.Person;
import com.apiAP.app.models.WorkExperience;
import com.apiAP.app.services.IImageService;
import com.apiAP.app.services.IPersonService;
import com.apiAP.app.services.IWorkExperienceService;
import com.apiAP.exceptions.BusinessException;
import com.apiAP.exceptions.RequestException;
import com.apiAP.exceptions.UserNotFoundException;
import com.google.gson.Gson;

import antlr.StringUtils;

@RestController
@RequestMapping("/workExperience")
@CrossOrigin(origins = /*"https://hosting-angular-d2f7a.web.app/"*/"*")
public class WorkExperienceController {

	private Image img;
	@Autowired
	private IWorkExperienceService workServ;
	@Autowired
	private IImageService imgServ;
	@Autowired
	private IPersonService perServ;
	
	@PostMapping("/admin/addWork")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addWork(@Valid @RequestBody WorkExperience workExp){
		try {
			workServ.createWork(workExp);
		    return new ResponseEntity<>("Work Experience with name '"+workExp.getWorkName()+" ' successfully add to "+workExp.getWorkPerson().getName()+" "+workExp.getWorkPerson().getSurname()+"",HttpStatus.OK);
			}catch(NullPointerException e) {
			throw new RequestException("You send any field with null information.", HttpStatus.NOT_FOUND, "p-404");
		}catch(DataIntegrityViolationException e) {
			throw new RequestException("Some field has an error, please do not violate the website.",HttpStatus.BAD_REQUEST,"P-300");
		}
		}
	
	@GetMapping("/listWorks")
	@ResponseBody
	public List<WorkExperience> getAllWorks(){
		return workServ.workList();
	}
	
	@DeleteMapping("/admin/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteWork(@Valid @PathVariable (value="id") Long idWork){
			try {
				workServ.deleteWork(idWork);
				
				return new ResponseEntity<>("Work with id "+idWork+" successfully deleted.", HttpStatus.OK);
			}catch(EmptyResultDataAccessException e) {
				throw new UserNotFoundException("Work with id "+idWork+" not exists", "p-404", HttpStatus.BAD_REQUEST);
			}
		
	}
	
	
	//Create Img
	@PostMapping(value = "/admin/addWork/imagen")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addWorkimg( @RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value="workName") String workName,
			@RequestParam(value="company") String company,
			@RequestParam(value="date_until") String dateUntil,
			@RequestParam(value="date_from") String dateFrom,
			@RequestParam(value="description") String description,
			@RequestParam(value="workPersonName") String workPersonName,
			@RequestParam(value="workPersonSurname") String workPersonSurname,
			@RequestParam(value="workPersonDescription") String workPersonDescription,
			@RequestParam(value="workPersonDateOfBirth") String workPersonDateOfBirth,
			@RequestParam(value="workPersonCity") String workPersonCity,
			@RequestParam(value="workPersonCountry") String workPersonCountry,
			@RequestParam(value="workPersonId") String workPersonId
			)throws IOException, ParseException{
	try {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date until = format.parse(dateUntil);
		Date from = format.parse(dateFrom);
		Date birth = format.parse(workPersonDateOfBirth);
		String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
	    Image FileDB = new Image(fileName, file.getContentType(), file.getBytes());
	    System.out.println(FileDB);
	    imgServ.saveImg(FileDB);
	    
	    Person per = new Person();
	    per.setIdPer(Long.parseLong(workPersonId));
	    per.setName(workPersonName);
	    per.setSurname(workPersonSurname);
	    per.setDateOfBirth(birth);
	    per.setCity(workPersonCity);
	    per.setCountry(workPersonCountry);
	    per.setDescription(workPersonDescription);
	    
	    
	    		
	    WorkExperience workExp = new WorkExperience();
	    workExp.setWorkName(workName);
	    workExp.setCompany(company);	
	    workExp.setDateUntil(until);
	    workExp.setDateFrom(from);
	    workExp.setWorkPerson(per);
	    workExp.setDescription(description);
	    workExp.setImg(FileDB);
	    workServ.createWork(workExp);
	
	    return new ResponseEntity<>("Work Experience with name '"+workExp.getWorkName()+" ' successfully add to "+per.getName()+" "+per.getSurname()+"",HttpStatus.OK);
	}catch(NullPointerException e) {
		throw new RequestException("You send any field with null information.", HttpStatus.NOT_FOUND, "p-404");
	}catch(DataIntegrityViolationException e) {
		throw new RequestException("Some field has an error, please do not violate the website.",HttpStatus.BAD_REQUEST,"P-300");
	}
	}


	@PostMapping("/admin/editExperience/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> editExperience(
			@PathVariable (value = "id") Long idWork,
			@RequestParam(value="workName") String workName,
			@RequestParam(value="company") String company,
			@RequestParam(value="date_until") String date_until,
			@RequestParam(value="date_from") String dateFrom,
			@RequestParam(value="description") String description,
			@RequestParam(value="workPersonId") String idPer) throws ParseException{
			
			WorkExperience work = workServ.findWorkById(idWork);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date until = format.parse(date_until);
			Date from = format.parse(dateFrom);
		
			work.setWorkName(workName);
			work.setCompany(company);
			work.setDateFrom(from);
			work.setDateUntil(until);
			work.setDescription(description);
			Person per = perServ.findByID(Long.parseLong(idPer));
	
			work.setWorkPerson(per);
			workServ.createWork(work);
			return new ResponseEntity<>("Work with id: "+idWork+" has edited.",HttpStatus.OK);

			
	}
	
	@GetMapping("/getExperience/{id}")
	public WorkExperience getWorkInfo(@PathVariable (value="id") Long id) {
		try {
			WorkExperience work =  workServ.findWorkById(id);
			return work;

		}catch(NoSuchElementException e) {
			throw new RequestException("Work with id: "+id+" not found.", HttpStatus.NOT_FOUND, "404");
		}
	}


}
