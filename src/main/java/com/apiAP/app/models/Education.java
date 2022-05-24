package com.apiAP.app.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idEducation;
	
	@Basic
	@NotEmpty(message = "the field must not be empty or null")
	private String name;
	
	@NotEmpty(message = "the field must not be empty or null")
	private String area;
	
	@Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date dateOfGraduation;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_person")
	@JsonBackReference(value = "education - person")
	private Person educPerson;

	public Education() {
		super();
	}

	public Education(Long idEducation, String name, String area, Date dateOfGraduation, Person educPerson) {
		super();
		this.idEducation = idEducation;
		this.name = name;
		this.area = area;
		this.dateOfGraduation = dateOfGraduation;
		this.educPerson = educPerson;
	}

	public Long getIdEducation() {
		return idEducation;
	}

	public void setIdEducation(Long idEducation) {
		this.idEducation = idEducation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getDateOfGraduation() {
		return dateOfGraduation;
	}

	public void setDateOfGraduation(Date dateOfGraduation) {
		this.dateOfGraduation = dateOfGraduation;
	}

	public Person getEducPerson() {
		return educPerson;
	}

	public void setEducPerson(Person educPerson) {
		this.educPerson = educPerson;
	}
	
	
	
	
}
