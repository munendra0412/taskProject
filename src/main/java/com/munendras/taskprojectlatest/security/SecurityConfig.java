package com.munendras.taskprojectlatest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	RequestFilter requestFilter;
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
	    		   .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/api/auth/**")
	    		   .permitAll()
	    		   .anyRequest()
	    		   .authenticated());
	    http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
	    		   return http.build();    
	}
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
    	return auth.getAuthenticationManager();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    

}
