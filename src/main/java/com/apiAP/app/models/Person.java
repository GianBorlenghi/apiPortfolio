package com.apiAP.app.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

	@Entity
	public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPer;
	
	@Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;
	
	@Basic
	@NotEmpty(message = "the field must not be empty or null")
	@Size(min = 3 , message = "the name must have more than 3 letters")
	private String name;
	
	@NotEmpty(message = "the field must not be empty or null")
	private String surname;
	
	
	
	@NotEmpty(message = "the field must not be empty or null")
	@Size(max = 500, message = "the description has a maximum of 500 letters")
	private String description;
	
	@NotEmpty(message = "the field must not be empty or null")
	private String city;
	
	@OneToMany(mappedBy = "workPerson", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "person - work")
	private List <WorkExperience> listWorks = new ArrayList<WorkExperience>();
	
	@OneToMany(mappedBy = "techPerson", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "person - tech")
	private List <Technology> listTech = new ArrayList<Technology>();
	
	@OneToMany(mappedBy = "educPerson",cascade = CascadeType.ALL)
	@JsonManagedReference(value = "education - person")
	private List <Education> listEducation = new ArrayList<Education>();
	
	@OneToMany(mappedBy = "projectPerson",cascade = CascadeType.ALL)
	@JsonManagedReference(value = "person - project")
	private List <Project> listProject = new ArrayList<Project>();

	
	public Person() {
		super();
	}





	public Person(Long idPer,Date dateOfBirth,
			@NotEmpty(message = "the field must not be empty or null") @Size(min = 3, message = "the name must have more than 3 chars") String name,
			@NotEmpty(message = "the field must not be empty or null") String surname,
			@NotEmpty(message = "the field must not be empty or null") @Size(max = 500, message = "the description has a maximum of 500 chars") String description,
			@NotEmpty(message = "the field must not be empty or null") String city, List<WorkExperience> listWorks,
			List<Technology> listTech, List<Education> listEducation, List<Project> listProject) {
		super();
		this.idPer = idPer;
		this.dateOfBirth = dateOfBirth;
		this.name = name;
		this.surname = surname;
		this.description = description;
		this.city = city;
		this.listWorks = listWorks;
		this.listTech = listTech;
		this.listEducation = listEducation;
		this.listProject = listProject;
	}





	public Long getIdPer() {
		return idPer;
	}


	public void setIdPer(Long idPer) {
		this.idPer = idPer;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public List<WorkExperience> getListWorks() {
		return listWorks;
	}


	public void setListWorks(List<WorkExperience> listWorks) {
		this.listWorks = listWorks;
	}


	public List<Technology> getListTech() {
		return listTech;
	}


	public void setListTech(List<Technology> listTech) {
		this.listTech = listTech;
	}


	public List<Education> getListEducation() {
		return listEducation;
	}


	public void setListEducation(List<Education> listEducation) {
		this.listEducation = listEducation;
	}


	public List<Project> getListProject() {
		return listProject;
	}


	public void setListProject(List<Project> listProject) {
		this.listProject = listProject;
	}



	


	
	
}
