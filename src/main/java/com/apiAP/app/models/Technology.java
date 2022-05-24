package com.apiAP.app.models;


import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Technology {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTechnology;
	
	@Basic
	@Column(unique=true)
	@NotEmpty(message = "the field must not be empty or null")
	private String nameTechnology;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_person")
	@JsonBackReference(value = "person - tech")
	private Person techPerson;
	
	@OneToMany(mappedBy = "projTechn", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "technproj_technology")
	private List <TechProject> listProjectXTech;
	
	public Technology() {
		super();
	}

	public Technology(Long idTechnology, String nameTechnology, Person techPerson, List<TechProject> listProjectXTech) {
		super();
		this.idTechnology = idTechnology;
		this.nameTechnology = nameTechnology;
		this.techPerson = techPerson;
		this.listProjectXTech = listProjectXTech;
	}

	public Long getIdTechnology() {
		return idTechnology;
	}

	public void setIdTechnology(Long idTechnology) {
		this.idTechnology = idTechnology;
	}

	public String getNameTechnology() {
		return nameTechnology;
	}

	public void setNameTechnology(String nameTechnology) {
		this.nameTechnology = nameTechnology;
	}

	public Person getTechPerson() {
		return techPerson;
	}

	public void setTechPerson(Person techPerson) {
		this.techPerson = techPerson;
	}

	public List<TechProject> getListProjectXTech() {
		return listProjectXTech;
	}

	public void setListProjectXTech(List<TechProject> listProjectXTech) {
		this.listProjectXTech = listProjectXTech;
	}


	

}
