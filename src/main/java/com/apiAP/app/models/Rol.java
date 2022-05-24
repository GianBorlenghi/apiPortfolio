package com.apiAP.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "rols")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRol;
	
	@Column(unique=true)
	@NotEmpty(message = "the field must not be empty or null")
	private String nombre;


	public Rol() {
		super();
	}

	public Rol(Long id, String nombre) {
		super();
		this.idRol = id;
		this.nombre = nombre;
	}

	public Rol(String name) {
		this.nombre = name;
	}

	public Long getId() {
		return idRol;
	}

	public void setId(Long id) {
		this.idRol = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
