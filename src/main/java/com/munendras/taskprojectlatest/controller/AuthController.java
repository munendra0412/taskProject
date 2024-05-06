package com.munendras.taskprojectlatest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.munendras.taskprojectlatest.entity.Users;
import com.munendras.taskprojectlatest.payload.LoginResponse;
import com.munendras.taskprojectlatest.payload.UserCreateResponse;
import com.munendras.taskprojectlatest.payload.UserDto;
import com.munendras.taskprojectlatest.security.CustomUserDetailsService;
import com.munendras.taskprojectlatest.security.JwtToken;
import com.munendras.taskprojectlatest.service.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtToken jwtToken;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService; 
	
	
	
	@PostMapping("create")
	public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserDto userDto) {
		UserCreateResponse res = new UserCreateResponse();
		
        // Check if any mandatory field is missing
        List<String> missingFields = getMissingFields(userDto);
        if (!missingFields.isEmpty()) {
            res.setMessagecode(400);
            res.setStatus("failed");
            res.setMessage("Missing mandatory fields: " + missingFields);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        
		Users user = userService.getUserInfoByEmail(userDto.getEmail());
		System.out.println("user data comming" + user);
		if(user == null) {
			System.out.println("comming in if");
			Users savedUser = userService.createUser(userDto);
			res.setMessagecode(200);
			res.setStatus("success");
			res.setMessage("User Cerated Successfully");
			res.setUserInfo(savedUser);
		}else {
			res.setMessagecode(202);
			res.setStatus("failed");
			res.setMessage("Email Already Available, User Creation Failed");
		}
		
		return ResponseEntity.ok(res);
	}
	
    // Method to check missing mandatory fields
    private List<String> getMissingFields(UserDto userDto) {
        List<String> missingFields = new ArrayList<>();
        if (userDto.getName() == null || userDto.getName().isEmpty()) {
            missingFields.add("name");
        }
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            missingFields.add("email");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            missingFields.add("password");
        }
        return missingFields;
    }
	
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> userLogin(@RequestBody UserDto userDto) throws AuthenticationException {
        // Perform authentication
		org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
		);
		
		System.out.println(authentication);

		// If authentication successful, set authentication in SecurityContext
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(userDto.getEmail());
        String token = jwtToken.generateToken(userDetails);
        LoginResponse loginRes = new LoginResponse();
        loginRes.setStatus("success");
        loginRes.setToken(token);
        return ResponseEntity.ok().body(loginRes);
    }

}
