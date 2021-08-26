package com.t2dstudio.springsecure.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.t2dstudio.springsecure.domain.Role;
import com.t2dstudio.springsecure.domain.User;
import com.t2dstudio.springsecure.repo.RoleRepo;
import com.t2dstudio.springsecure.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor //dependency(constructor) injection for the repo class
@Transactional
@Slf4j  //for logging
public class UserServiceImpl implements UserService, UserDetailsService{

	
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) {
			log.info("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		}else {
			log.info("Use found in the database: {}", username);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role ->{authorities.add(new SimpleGrantedAuthority(role.getName()));
			});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
				,authorities);
	}
	@Override
	public User saveUser(User user) {
		log.info("saving new user {} to the db", user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("saving new role {} to the db", role.getName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("adding role {} to user {}", roleName, username);
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);
		//you dont need to save this to the db bcos @transactional will do that automatically
	}

	@Override
	public User getUser(String username) {
		log.info("Feyching user {}", username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Feyching all users ");
		return userRepo.findAll();
	}

	

}
