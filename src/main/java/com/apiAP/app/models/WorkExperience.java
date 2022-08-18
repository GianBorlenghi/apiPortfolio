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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "experience")
public class WorkExperience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idWork;
	
	@Basic
	@Column()
	@NotEmpty(message = "the field must not be empty or null")
	private String workName;
	
	@NotEmpty(message = "the field must not be empty or null")
	private String company;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date_from;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date_until;
	

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_person")
	@JsonBackReference(value = "person - work")
	private Person workPerson;
	
	@OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "img_id")
    private Image img;	

	
	public WorkExperience() {
		super();
	}



	public WorkExperience(Long idWork, @NotEmpty(message = "the field must not be empty or null") String workName,
			@NotEmpty(message = "the field must not be empty or null") String company, String description,
			Date date_from, Date date_until, Person workPerson,Image img) {
		super();
		this.idWork = idWork;
		this.workName = workName;
		this.company = company;
		this.description = description;
		this.date_from = date_from;
		this.date_until = date_until;
		this.workPerson = workPerson;
		this.img = img;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public Image getImg() {
		return img;
	}



	public void setImg(Image img) {
		this.img = img;
	}

	





}
