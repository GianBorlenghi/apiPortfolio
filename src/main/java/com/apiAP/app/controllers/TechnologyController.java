package com.apiAP.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.apiAP.app.models.Technology;
import com.apiAP.app.security.JwtAuthenticationFilter;
import com.apiAP.app.services.ITechnologyService;
import com.apiAP.exceptions.RequestException;
import com.apiAP.exceptions.BusinessException;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;

@RestController
@RequestMapping(value = "technologys")
@CrossOrigin(origins = /*"https://hosting-angular-d2f7a.web.app/"*/"*")
public class TechnologyController {
	
	@Autowired
	private ITechnologyService tecServ;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("admin/add")
	public ResponseEntity<?> addTec(@Valid @RequestBody Technology tec) {
		
			tecServ.saveTechnology(tec);
			return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@DeleteMapping("/admin/deleteTech/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteTechnology(@PathVariable (value = "id") Long id){
		try{
			tecServ.deleteTechnology(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(EmptyResultDataAccessException a) {
			throw new RequestException("Id tech not found", HttpStatus.NOT_FOUND, "p-404");
		}catch(DataIntegrityViolationException e) {
			throw new BusinessException("Can't delete technoolgy with id: "+id+" because it is used in a project.","p-400",HttpStatus.BAD_REQUEST);
		}
	}

	
	@GetMapping("/getAllTech")
	public List<Technology> getAllTech(){
		return tecServ.getAllTech();
	}
}
