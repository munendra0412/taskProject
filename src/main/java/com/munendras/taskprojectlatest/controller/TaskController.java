package com.munendras.taskprojectlatest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munendras.taskprojectlatest.entity.Tasks;
import com.munendras.taskprojectlatest.entity.Users;
import com.munendras.taskprojectlatest.payload.TaskDto;
import com.munendras.taskprojectlatest.service.TaskService;
import com.munendras.taskprojectlatest.service.UserService;

@RequestMapping("api/task")
@RestController
public class TaskController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping("{userId}/create")
	public ResponseEntity<Tasks> createTask(@RequestBody TaskDto taskDto, @PathVariable Long userId) {
		Users user = userService.getUserInfoById(userId);
		System.out.println(user);
		Tasks Convertedtask = modelMapper.map(taskDto, Tasks.class);
		Convertedtask.setUsers(user);
		taskService.createTask(Convertedtask);
		return ResponseEntity.ok(Convertedtask);
		
	}

}
