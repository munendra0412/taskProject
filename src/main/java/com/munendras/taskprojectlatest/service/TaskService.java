package com.munendras.taskprojectlatest.service;

import org.springframework.stereotype.Service;

import com.munendras.taskprojectlatest.entity.Tasks;

@Service
public interface TaskService {
	
	public void createTask(Tasks task);

}
