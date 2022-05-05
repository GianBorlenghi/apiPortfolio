package com.apiAP.app.services;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.Education;
import com.apiAP.app.repositories.IEducationRepo;
import com.apiAP.exceptions.RequestException;

@Service
public class EducationService implements IEducationService {
	
	@Autowired
	private IEducationRepo eduRepo;

	@Override
	public void saveEducation(Education edu) {
		eduRepo.save(edu);
	}

	@Override
	public void deleteEducation(Long id) {
		Education edu = eduRepo.findById(id)
				.orElseThrow( 
						() -> new RequestException("Education with id: *"+id+ "* doesn't exist.", HttpStatus.NOT_FOUND,"P-404")
						);
		eduRepo.deleteById(id);
	}

	@Override
	public Education findById(Long id) {
		return eduRepo.findById(id)
				.orElseThrow(
						() -> new RuntimeException("Id doesn't exists.")
							);
		
	}

}
