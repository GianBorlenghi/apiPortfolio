package com.apiAP.app.services;

import org.springframework.http.ResponseEntity;

import com.apiAP.app.models.WorkExperience;

public interface IWorkExperienceService {
	
	public void createWork(WorkExperience workExperience);
	public void deleteWork(Long id);
	public void editWork(Long id);

}
