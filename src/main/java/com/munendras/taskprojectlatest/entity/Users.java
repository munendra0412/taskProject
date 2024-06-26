package com.munendras.taskprojectlatest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="users")
public class Users {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(name="name",nullable = false)
	    private String name;
	    @Column(name="email",nullable = false)
	    private String email;
	    @Column(name="password",nullable = false)
	    private String password;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public Users() {
			super();
			this.name = name;
			this.email = email;
			this.password = password;
		}
		
		
		
		public Users(String name, String email, String password) {
			super();
			this.name = name;
			this.email = email;
			this.password = password;
		}
		
		@Override
		public String toString() {
			return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
		}
		

}
