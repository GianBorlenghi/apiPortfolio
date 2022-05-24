package com.apiAP.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.WorkExperience;
import com.apiAP.app.repositories.IWorkExperienceRepo;
import com.apiAP.exceptions.BusinessException;

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

		
	}

	@Override
	public List<WorkExperience> workList() {
		List<WorkExperience> works = workRepo.findAll();
		if(works.size() >=1) {
			return works; 
		}else {
			throw new BusinessException("At the moment, the section 'Work Experience' is null", "P-300", HttpStatus.NOT_FOUND);
		}
		
		
	}

}
