package com.munendras.taskprojectlatest.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.munendras.taskprojectlatest.entity.Users;
import com.munendras.taskprojectlatest.payload.UserDto;
import com.munendras.taskprojectlatest.repository.UsersRepository;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Users createUser(UserDto userDto) {
		System.out.println("User DTO "+ userDto);
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Users user = modelMapper.map(userDto, Users.class);
		System.out.println("User info " +user);
		return usersRepository.save(user);
	}

	@Override
	public Users getUserInfoByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	@Override
	public Users getUserInfoById(Long userId) {
		return usersRepository.findById(userId).orElse(null);
	}

}
