package com.apiAP.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.TechProject;
import com.apiAP.app.repositories.ITechProjectRepo;
import com.apiAP.exceptions.BusinessException;

@Service
public class TechProjectServ implements ITechProjectServ {

	@Autowired
	private ITechProjectRepo tRepo;

	@Override
	public void saveTechProj(TechProject tpro) {
		List<TechProject> tp = tRepo.findAll();
		
		for(TechProject tpr : tp) {
			if(tpr.getProjTechn().getIdTechnology() == tpro.getProjTechn().getIdTechnology()
					&& tpr.getTechnProj().getIdproject() == tpro.getTechnProj().getIdproject()) {
				throw new BusinessException("The project "+tpro.getTechnProj().getIdproject()+""
						+ " already have the technology with id "+tpro.getProjTechn().getIdTechnology(), 
						"P-300", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		tRepo.save(tpro);
	}
	
	
}
