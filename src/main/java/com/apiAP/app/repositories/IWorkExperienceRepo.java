package com.apiAP.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiAP.app.models.WorkExperience;

@Repository
public interface IWorkExperienceRepo extends JpaRepository<WorkExperience, Long>{

}
