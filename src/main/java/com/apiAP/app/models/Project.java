package com.apiAP.app.models;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "projects")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idproject;
	
	@Basic
	@Column(unique=true)
	private String project_name;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_person")
	@JsonBackReference(value = "person - project")
	private Person projectPerson;
	
	@OneToMany(mappedBy = "technProj", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "technproj_project")
	private List <TechProject> listTechXProject; 
	

	public Project() {
		super();
	}


	public Project(Long idproject, String project_name, Person projectPerson, List<TechProject> listTechXProject) {
		super();
		this.idproject = idproject;
		this.project_name = project_name;
		this.projectPerson = projectPerson;
		this.listTechXProject = listTechXProject;
	}


	public Long getIdproject() {
		return idproject;
	}


	public void setIdproject(Long idproject) {
		this.idproject = idproject;
	}


	public String getProject_name() {
		return project_name;
	}


	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}


	public Person getProjectPerson() {
		return projectPerson;
	}


	public void setProjectPerson(Person projectPerson) {
		this.projectPerson = projectPerson;
	}


	public List<TechProject> getListTechXProject() {
		return listTechXProject;
	}


	public void setListTechXProject(List<TechProject> listTechXProject) {
		this.listTechXProject = listTechXProject;
	}

	

	

	
	
}
