package com.apiAP.app.services;

import com.apiAP.app.models.Education;

public interface IEducationService {
	public void saveEducation(Education edu);
	public void deleteEducation(Long id);
	public Education findById(Long id);
}
