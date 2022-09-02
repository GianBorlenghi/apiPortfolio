package com.apiAP.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.apiAP.app.models.Image;
import com.apiAP.app.models.Person;
import com.apiAP.app.models.Project;
import com.apiAP.app.models.Technology;
import com.apiAP.app.services.IImageService;
import com.apiAP.app.services.IPersonService;
import com.apiAP.app.services.IProjectService;
import com.apiAP.exceptions.BusinessException;
import com.apiAP.exceptions.RequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = /*"https://hosting-angular-d2f7a.web.app/"*/"*")

public class ProjectController {

	@Autowired
	private IProjectService projServ;
	@Autowired
	private IImageService imgServ;
	@Autowired
	private IPersonService perServ;

	@PostMapping("admin/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveProj(@RequestParam (value="file") MultipartFile file,
							   		  @RequestParam(value="url",required=false) String url,
							   		  @RequestParam(value="urlGit",required=false) String urlGit,
									  @RequestParam(value="projectName") String projectName,
									  @RequestParam(value="description") String description,
									  @RequestParam(value="idPer") String idPer,
									  @RequestParam(value="techList") String techList) throws IOException {
		
		String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
	    Image FileDB = new Image(fileName, file.getContentType(), file.getBytes());
	    System.out.println(FileDB);
	    imgServ.saveImg(FileDB);
	    
	    Person per = perServ.findByID(Long.parseLong(idPer));
	    
	    Project proj = new Project();
	    proj.setProject_name(projectName);
	    proj.setProjectPerson(per);
	    proj.setImg(FileDB);
	    proj.setUrl(url);
	    proj.setUrlGit(urlGit);
	    proj.setDescription(description);
	    
	    //Creo nueva lista de technologias, traido desde el front.
        ObjectMapper mapper = new ObjectMapper();
        Technology[] techXProj= mapper.readValue(techList, Technology[].class);
        List<Technology> techListPro = new ArrayList<Technology>();
        for(Technology tech : techXProj) {
    	    techListPro.add(tech);
        }
        
        proj.setListTechXProject(techListPro);
       projServ.saveProject(proj);

		return new ResponseEntity<>("Project successfully added",HttpStatus.OK);

		}

	@PostMapping("admin/addWithouthImage")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveProj(@RequestParam(value="projectName") String projectName,
			  						  @RequestParam(value="url",required=false) String url,
			  						  @RequestParam(value="urlGit",required=false) String urlGit,
									  @RequestParam(value="description") String description,
									  @RequestParam(value="idPer") String idPer,
			 						  @RequestParam(value="techList") String techList) throws JsonMappingException, JsonProcessingException{
	    Person per = perServ.findByID(Long.parseLong(idPer));
	    
	    Project proj = new Project();
	    proj.setProject_name(projectName);
	    proj.setProjectPerson(per);
	    proj.setUrl(url);
	    proj.setUrlGit(urlGit);
		proj.setDescription(description);
		
	     ObjectMapper mapper = new ObjectMapper();
	        Technology[] techXProj= mapper.readValue(techList, Technology[].class);
	        List<Technology> techListPro = new ArrayList<Technology>();
	        for(Technology tech : techXProj) {
	    	    techListPro.add(tech);
	        }
	        
	        proj.setListTechXProject(techListPro);
	       projServ.saveProject(proj);

			return new ResponseEntity<>("Project successfully added",HttpStatus.OK);
}
	
	@GetMapping("getProject/{id}")
	public Project getProject(@PathVariable(value = "id") Long id) {
		return projServ.findProjectById(id);
	}
	
	@DeleteMapping("deleteProject/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteProject(@PathVariable(value = "id") Long id){
		
		projServ.deleteProject(id);
		return new ResponseEntity<>("successfully deleted",HttpStatus.OK);
	}
}
