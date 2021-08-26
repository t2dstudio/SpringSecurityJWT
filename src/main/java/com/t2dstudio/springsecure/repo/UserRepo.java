package com.t2dstudio.springsecure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t2dstudio.springsecure.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
