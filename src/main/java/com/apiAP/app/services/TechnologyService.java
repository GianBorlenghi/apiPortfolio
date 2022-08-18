package com.apiAP.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.apiAP.app.models.Education;
import com.apiAP.app.models.Technology;
import com.apiAP.app.repositories.ITechnologyRepo;
import com.apiAP.exceptions.RequestException;

import io.jsonwebtoken.MalformedJwtException;

@Service
public class TechnologyService implements ITechnologyService {

	@Autowired
	private ITechnologyRepo repoTec;
	@Override
	public void saveTechnology(Technology tec) {
		
		repoTec.save(tec);
		
	}
	
	
	@Override
	public void deleteTechnology(Long id) {
	
			repoTec.deleteById(id);
		
		
	}


	@Override
	public List<Technology> getAllTech() {

		return repoTec.findAll();
		
	}
	
	

}
