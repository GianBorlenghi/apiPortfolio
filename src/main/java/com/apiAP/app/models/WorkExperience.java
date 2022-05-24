package com.apiAP.app.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "experience")
public class WorkExperience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idWork;
	
	@Basic
	@Column(unique=true)
	@NotEmpty(message = "the field must not be empty or null")
	private String workName;
	
	@NotEmpty(message = "the field must not be empty or null")
	private String company;
	
	@NotEmpty(message = "the field must not be empty or null")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date_from;
	
	@NotEmpty(message = "the field must not be empty or null")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date_until;
	
	@NotEmpty(message = "the field must not be empty or null")
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_person")
	@JsonBackReference(value = "person - work")
	private Person workPerson;

	public WorkExperience() {
		super();
	}

	public WorkExperience(Long idWork, String workName, String company, Date date_from, Date date_until, Person workPerson) {
		super();
		this.idWork = idWork;
		this.workName = workName;
		this.company = company;
		this.date_from = date_from;
		this.date_until = date_until;
		this.workPerson = workPerson;
	}

	public Long getIdWork() {
		return idWork;
	}

	public void setIdWork(Long idWork) {
		this.idWork = idWork;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getDateFrom() {
		return date_from;
	}

	public void setDateFrom(Date date_from) {
		this.date_from = date_from;
	}

	public Date getDateUntil() {
		return date_until;
	}

	public void setDateUntil(Date date_until) {
		this.date_until = date_until;
	}

	public Person getWorkPerson() {
		return workPerson;
	}

	public void setWorkPerson(Person workPerson) {
		this.workPerson = workPerson;
	}
	
	



}
