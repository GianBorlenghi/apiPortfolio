package com.apiAP.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.Project;
import com.apiAP.app.repositories.IProjectRepo;

@Service
public class ProjectService implements IProjectService{

	@Autowired
	private IProjectRepo projRepo;

	@Override
	public void saveProject(Project proj) {
		projRepo.save(proj);
	}

	@Override
	public void deleteProject(Long idProject) {

		projRepo.deleteById(idProject);
	}

	@Override
	public Project findProjectById(Long idProject) {

		return projRepo.findById(idProject).orElseThrow();
	}

	@Override
	public List<Project> getAllProject() {

		return projRepo.findAll();
	}
	
	
}
