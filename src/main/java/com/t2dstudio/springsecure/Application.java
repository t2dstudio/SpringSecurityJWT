package com.t2dstudio.springsecure;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.t2dstudio.springsecure.domain.Role;
import com.t2dstudio.springsecure.domain.User;
import com.t2dstudio.springsecure.service.UserService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	//whenever the application runs this bean will be available for use
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	//this will load after the application initialises....creating methods
	@Bean
	CommandLineRunner run (UserService userService) {
		return args ->{
			userService.saveRole(new Role(null, "ROLE_USER" ));
			userService.saveRole(new Role(null, "ROLE_MANAGER" ));
			userService.saveRole(new Role(null, "ROLE_ADMIN" ));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN" ));
			
			userService.saveUser(new User(null, "Kenny Ade", "kenny", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Tee Bobo", "tee", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Dami Oye", "dami", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Remi Oye", "remi", "1234", new ArrayList<>()));
			
			userService.addRoleToUser("kenny", "ROLE_USER");
			userService.addRoleToUser("tee", "ROLE_MANAGER");
			userService.addRoleToUser("dami", "ROLE_ADMIN");
			userService.addRoleToUser("kenny", "ROLE_ADMIN");
			userService.addRoleToUser("kenny", "ROLE_SUPER_ADMIN");
			
		};
	}

}
