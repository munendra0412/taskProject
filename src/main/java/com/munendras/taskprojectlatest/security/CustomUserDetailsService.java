package com.munendras.taskprojectlatest.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.munendras.taskprojectlatest.entity.Users;
import com.munendras.taskprojectlatest.repository.UsersRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Comming in load user by username");
		System.out.println(username);
		Users user = usersRepository.findByEmail(username);
		System.out.println(user);
		Set<String> roles = new HashSet<String>();
		roles.add("ROLE_ADMIN");
		
		return new User(user.getEmail(),user.getPassword(),userAuthority(roles));
	}
	
	
	private Collection<? extends GrantedAuthority> userAuthority(Set<String> roles){
		return roles.stream().map(
				role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}

}