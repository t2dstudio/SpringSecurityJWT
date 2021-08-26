package com.t2dstudio.springsecure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.t2dstudio.springsecure.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
