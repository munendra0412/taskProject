package com.munendras.taskprojectlatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munendras.taskprojectlatest.entity.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long>{

}
