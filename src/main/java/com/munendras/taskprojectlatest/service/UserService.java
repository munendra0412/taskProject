package com.munendras.taskprojectlatest.service;

import org.springframework.stereotype.Service;

import com.munendras.taskprojectlatest.entity.Users;
import com.munendras.taskprojectlatest.payload.UserDto;

@Service
public interface UserService {
	
	public Users createUser(UserDto userDto);
	public Users getUserInfoByEmail(String email);
	public Users getUserInfoById(Long userId);

}
