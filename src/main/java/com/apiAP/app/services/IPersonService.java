package com.apiAP.app.services;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.Education;
import com.apiAP.app.models.Person;
import com.apiAP.app.models.Project;
import com.apiAP.app.models.Technology;
import com.apiAP.app.models.WorkExperience;

@Repository
public interface IPersonService {
	
	public void savePerson(Person per);
	public void editPerson(Person per);
	public void deletePerson(Long id);
	public List<Person> getAllPerson();
	public Person findByID(Long id);
	public List  <Education> viewAllEducations(Long id);
	public List <Technology> viewAllTechnology(Long id);
	public List <WorkExperience> viewAllWork(Long id);
	public List <Project> viewAllProjects(Long id);
	
}
