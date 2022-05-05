package com.apiAP.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiAP.app.models.Project;

@Repository
public interface IProjectRepo extends JpaRepository<Project, Long>{

}
