package com.t2dstudio.springsecure.service;

import java.util.List;

import com.t2dstudio.springsecure.domain.Role;
import com.t2dstudio.springsecure.domain.User;


public interface UserService {
//creating methods for our user	
User saveUser(User user);

Role saveRole(Role role);

void addRoleToUser(String username, String roleName);

User getUser(String username);

List<User> getUsers();
}
