package com.apiAP.app.services;

import java.util.List;

import com.apiAP.app.models.Project;

public interface IProjectService {
	
	public void saveProject(Project proj);
	public void deleteProject(Long idProject);
	public Project findProjectById(Long idProject);
	public List<Project> getAllProject();

}
