package com.apiAP.app.services;

import java.io.FileNotFoundException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.Education;
import com.apiAP.app.models.Person;
import com.apiAP.app.models.Project;
import com.apiAP.app.models.Technology;
import com.apiAP.app.models.WorkExperience;
import com.apiAP.app.repositories.IPersonRepo;
import com.apiAP.exceptions.RequestException;

@Service
public class PersonService implements IPersonService {
	
	@Autowired
	private IPersonRepo repo;

	@Override
	public void savePerson(Person per) {
		repo.save(per);		
	}

	@Override
	public void editPerson(Person per) {
		repo.save(per);
	}

	@Override
	public Person findByID(Long id) {
		return repo.findById(id)
				.orElseThrow(
						()-> new RequestException
						("Person with id: "+id+" not found", HttpStatus.INTERNAL_SERVER_ERROR, "P-500")
						);
	}

	@Override
	public List<Education> viewAllEducations(Long id) {
		Person per = repo.findById(id)
			.orElseThrow(
						()-> new RequestException("Person with id: "+id+" not found", HttpStatus.INTERNAL_SERVER_ERROR, "P-500")	
					);
		return per.getListEducation();
	}

	@Override
	public List<Technology> viewAllTechnology(Long id) {
		Person per = repo.findById(id)
				.orElseThrow(
							()->  new RequestException("Person with id: "+id+" not found", HttpStatus.INTERNAL_SERVER_ERROR, "P-500")	
						);
			return per.getListTech();
	}

	@Override
	public List<WorkExperience> viewAllWork(Long id) {
		Person per = repo.findById(id)
				.orElseThrow(
							()->  new RequestException("Person with id: "+id+" not found", HttpStatus.INTERNAL_SERVER_ERROR, "P-500")
						);
			return per.getListWorks();
	}

	@Override
	public List<Project> viewAllProjects(Long id) {
		Person per = repo.findById(id)
				.orElseThrow(
							()->  new RequestException("Person with id: "+id+" not found", HttpStatus.INTERNAL_SERVER_ERROR, "P-500")	
						);
			return per.getListProject();
	}
	
}
