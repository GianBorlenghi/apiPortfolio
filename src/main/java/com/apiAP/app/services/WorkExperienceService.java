package com.apiAP.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.WorkExperience;
import com.apiAP.app.repositories.IWorkExperienceRepo;

@Service
public class WorkExperienceService implements IWorkExperienceService {

	@Autowired
	private IWorkExperienceRepo workRepo;
	
	@Override
	public void createWork(WorkExperience workExperience) {

		workRepo.save(workExperience);
	}

	@Override
	public void deleteWork(Long id) {
		WorkExperience work = workRepo.getById(id);
		workRepo.delete(work);
		
	}

	@Override
	public void editWork(Long id) {
		// TODO Auto-generated method stub
		
	}

}
