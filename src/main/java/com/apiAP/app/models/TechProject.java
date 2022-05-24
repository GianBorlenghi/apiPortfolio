package com.apiAP.app.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonBackReference;

@RestController
@Entity
public class TechProject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtech_project;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_technology")
	@JsonBackReference(value = "technproj_technology")
	private Technology projTechn;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_project")
	@JsonBackReference(value = "technproj_project")
	private Project technProj;

	public TechProject() {
		super();
	}

	public TechProject(Long idtech_project, Technology projTechn, Project technProj) {
		super();
		this.idtech_project = idtech_project;
		this.projTechn = projTechn;
		this.technProj = technProj;
	}

	public Long getIdtech_project() {
		return idtech_project;
	}

	public void setIdtech_project(Long idtech_project) {
		this.idtech_project = idtech_project;
	}

	public Technology getProjTechn() {
		return projTechn;
	}

	public void setProjTechn(Technology projTechn) {
		this.projTechn = projTechn;
	}

	public Project getTechnProj() {
		return technProj;
	}

	public void setTechnProj(Project technProj) {
		this.technProj = technProj;
	}
	
	
}
