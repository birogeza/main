package com.bg.ticketwebshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bg.ticketwebshop.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findRoleByName(String theRoleName);
	
}
