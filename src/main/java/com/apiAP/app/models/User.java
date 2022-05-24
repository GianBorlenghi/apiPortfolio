package com.apiAP.app.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;

	@Basic
	@Column(unique=true)
	@NotEmpty(message = "the field must not be empty or null")
	@Email(message = "the typed email does not match the traditional format")
	private String mail;
	
	@NotEmpty(message = "the field must not be empty or null")
	private String pass;
	
	private boolean isOnline;
	

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private LocalDateTime lastConnection;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser"),
			inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol")
			)
	private Set<Rol> roles = new HashSet<>();

	public User() {
		super();
	}

	public User(Long idUser, String mail, String pass, Set<Rol> roles,boolean isOnline,LocalDateTime lastConnection) {
		super();
		this.idUser = idUser;
		this.mail = mail;
		this.pass = pass;
		this.roles = roles;
		this.isOnline = isOnline;
		this.lastConnection = lastConnection;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public LocalDateTime getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(LocalDateTime lastConnection) {
		this.lastConnection = lastConnection;
	}

	
	
}
