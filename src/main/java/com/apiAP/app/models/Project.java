package com.apiAP.app.models;

import java.util.ArrayList;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "projects")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idproject;
	
	@Basic
	@NotEmpty(message = "the field must not be empty or null")
	private String project_name;
	
	@Basic
	private String url;
	private String urlGit;
	
	
	@NotEmpty(message = "the field must not be empty or null")
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_person")
	@JsonBackReference(value = "person - project")
	private Person projectPerson;

	@JoinTable(
			name="tech_projects",
			joinColumns = @JoinColumn(name ="FK_PROJECT",nullable = false),
			inverseJoinColumns = @JoinColumn(name = "FK_TECHNOLOGY", nullable = false)
			)
	@ManyToMany
	private List <Technology> listTechXProject = new ArrayList<Technology>();
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "img_id")
    private Image img;	
	
	public Project() {
		super();
	}


	public Project(Long idproject, String project_name, Person projectPerson, List<Technology> listTechXProject,Image img,String url,String urlGit,String description) {
		super();
		this.idproject = idproject;
		this.project_name = project_name;
		this.projectPerson = projectPerson;
		this.listTechXProject = listTechXProject;
		this.img = img;
		this.url= url; 
		this.urlGit = urlGit;
		this.description = description;
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


	public List<Technology> getListTechXProject() {
		return listTechXProject;
	}


	public void setListTechXProject(List<Technology> listTechXProject) {
		this.listTechXProject = listTechXProject;
	}


	public Image getImg() {
		return img;
	}


	public void setImg(Image img) {
		this.img = img;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getUrlGit() {
		return urlGit;
	}


	public void setUrlGit(String urlGit) {
		this.urlGit = urlGit;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
}
