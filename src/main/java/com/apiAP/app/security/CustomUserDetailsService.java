package com.apiAP.app.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apiAP.app.models.Rol;
import com.apiAP.app.models.User;
import com.apiAP.app.services.IUserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    
	@Autowired
	private IUserService userServ;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User use = userServ.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(use.getMail(), use.getPass(),
			mapRols(use.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRols(Set<Rol> rols) {
		return rols.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}

}
