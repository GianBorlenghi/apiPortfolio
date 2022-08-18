package com.apiAP.app.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.apiAP.app.models.WorkExperience;

public interface IWorkExperienceService {
	
	public void createWork(WorkExperience workExperience);
	public void deleteWork(Long id);
	public WorkExperience findWorkById(Long id);
	public List<WorkExperience> workList();

}
