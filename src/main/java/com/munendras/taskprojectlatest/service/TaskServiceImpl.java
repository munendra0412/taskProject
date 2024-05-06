package com.munendras.taskprojectlatest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.munendras.taskprojectlatest.entity.Tasks;
import com.munendras.taskprojectlatest.repository.TaskRepository;


@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	TaskRepository taskRepository;

	@Override
	public void createTask(Tasks task) {
		taskRepository.save(task);
	}

}
