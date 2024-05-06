package com.munendras.taskprojectlatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.munendras.taskprojectlatest.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

	Users findByEmail(String email);

}
