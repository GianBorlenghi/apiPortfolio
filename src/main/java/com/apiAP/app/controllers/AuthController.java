package com.apiAP.app.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiAP.app.dto.LoginDTO;
import com.apiAP.app.dto.RegisterDTO;
import com.apiAP.app.models.Rol;
import com.apiAP.app.models.User;
import com.apiAP.app.repositories.IRolRepo;
import com.apiAP.app.security.JWTAuthResponseDTO;
import com.apiAP.app.security.JwtTokenProvider;
import com.apiAP.app.services.IRolService;
import com.apiAP.app.services.IUserService;
import com.apiAP.exceptions.BusinessException;
import com.apiAP.exceptions.RequestException;
import com.apiAP.exceptions.UserNotFoundException;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private IUserService userServ;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IRolService rolServ;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO){
		User user = userServ.findByUsername(loginDTO.getMail());
		if(user !=null) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getMail(), loginDTO.getPassword()));		
		SecurityContextHolder.getContext().setAuthentication(authentication);	
		String token = jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JWTAuthResponseDTO(token)); 
		}else {
			throw new UserNotFoundException("User not found", "P-404", HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/admin/register")
	public ResponseEntity<?> adminRegister(@RequestBody RegisterDTO registerDTO){
		if(userServ.emailExists(registerDTO.getMail())) {
			throw new BusinessException("e-mail alredy used.","p-300",HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			User user = new User();
			user.setMail(registerDTO.getMail());
			user.setPass(passwordEncoder.encode(registerDTO.getPassword()));
			
			Rol rols = rolServ.findByNameRol("ROLE_ADMIN");
		
			user.setRoles(Collections.singleton(rols));
			
			userServ.saveUser(user);
			return new ResponseEntity<>("User sucessffully register.", HttpStatus.OK);
		}
		
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> userRegister(@RequestBody RegisterDTO registerDTO){
		if(userServ.emailExists(registerDTO.getMail())) {
			return new ResponseEntity<>("That mail alredy used", HttpStatus.BAD_REQUEST);
		}else {
			User user = new User();
			user.setMail(registerDTO.getMail());
			user.setPass(passwordEncoder.encode(registerDTO.getPassword()));
			
			Rol rols = rolServ.findByNameRol("ROLE_USER");
		
			user.setRoles(Collections.singleton(rols));
			
			userServ.saveUser(user);
			return new ResponseEntity<>("User sucessffully register.", HttpStatus.OK);
		}
		
		
	}
	
}
