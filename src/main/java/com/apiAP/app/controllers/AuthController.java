package com.apiAP.app.controllers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import javax.imageio.spi.RegisterableService;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apiAP.app.dto.LoginDTO;
import com.apiAP.app.dto.RegisterDTO;
import com.apiAP.app.models.Person;
import com.apiAP.app.models.Rol;
import com.apiAP.app.models.User;
import com.apiAP.app.security.JWTAuthResponseDTO;
import com.apiAP.app.security.JwtTokenProvider;
import com.apiAP.app.services.IRolService;
import com.apiAP.app.services.IUserService;
import com.apiAP.app.util.MailSender;
import com.apiAP.exceptions.BusinessException;
import com.apiAP.exceptions.RequestException;
import com.apiAP.exceptions.UserNotFoundException;

@RestController
@CrossOrigin(
	    origins = "https://hosting-angular-d2f7a.web.app/",
	    allowedHeaders = {"Content-Type", "Authorization", "Access-Control-Allow-Origin"}
	    )
	 
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
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
		User user = userServ.findByUsername(loginDTO.getMail());
		if (user != null) {
			LocalDateTime time = LocalDateTime.now();
			user.setLastConnection(time);
			user.setOnline(true);
			userServ.saveUser(user);
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getMail(), loginDTO.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtTokenProvider.generateToken(authentication);

			return ResponseEntity.ok(new JWTAuthResponseDTO(token));
		} else {
			throw new UserNotFoundException("User not found", "P-404", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userServ.findByUsername(username);
			user.setOnline(false);
			userServ.saveUser(user);
			SecurityContextHolder.clearContext();
			return new ResponseEntity<> ("Logout successfully", HttpStatus.OK);
		} catch (NullPointerException ex) {
			throw new RequestException("No user conected", HttpStatus.NOT_FOUND, "P-300");
		}
	}

	@PostMapping("/admin/register")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> adminRegister(@RequestBody RegisterDTO registerDTO) throws AddressException, MessagingException {
		if (userServ.emailExists(registerDTO.getMail())) {
			throw new BusinessException("e-mail already used.", "p-300", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			User user = new User();
			user.setMail(registerDTO.getMail());
			user.setPass(passwordEncoder.encode(registerDTO.getPassword()));

			Rol rols = rolServ.findByNameRol("ROLE_ADMIN");

			user.setRoles(Collections.singleton(rols));

			userServ.saveUser(user);
			
			MailSender mail = new MailSender();
			mail.sendMail(registerDTO.getMail(),"¡ Welcome to my WebPage !" ,mail.mailBody(registerDTO.getMail()));
			
			return new ResponseEntity<>("New admin sucessffully register.", HttpStatus.OK);
		}

	}

	@PostMapping("/register")
	public ResponseEntity<?> userRegister(@RequestBody RegisterDTO registerDTO) throws AddressException, MessagingException{
		if (userServ.emailExists(registerDTO.getMail())) {
			return new ResponseEntity<>("That mail alredy used", HttpStatus.BAD_REQUEST);
		} else {
			User user = new User();
			user.setMail(registerDTO.getMail());
			user.setPass(passwordEncoder.encode(registerDTO.getPassword()));

			Rol rols = rolServ.findByNameRol("ROLE_USER");

			user.setRoles(Collections.singleton(rols));

			userServ.saveUser(user);
			
			MailSender mail = new MailSender();
			mail.sendMail(registerDTO.getMail(),"¡ Welcome to my WebPage !" ,mail.mailBody(registerDTO.getMail()));
			return new ResponseEntity<>("New user sucessffully registered.", HttpStatus.OK);
		}

	}
	
	@GetMapping("/userAvailableCheck/{username}")
	@ResponseBody
	public boolean userAvailabe(@PathVariable (value="username") String username) {
		
		if(userServ.findByUsername(username) != null) {
			return false;
		}else {
			return true;
		}
		
	}
	@GetMapping("/getUserInfo/{username}")
	@ResponseBody
	public Set<Rol> userInfo(@PathVariable (value="username") String username){
		try {
			
			User us =  userServ.findByUsername(username);
			return us.getRoles();
		}catch(NullPointerException ex) {
			throw new UserNotFoundException("No user found", "P-404", HttpStatus.NOT_FOUND);
		}
	}

}
