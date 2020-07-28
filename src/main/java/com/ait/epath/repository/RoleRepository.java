package com.ait.epath.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ait.epath.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRole(String role);
	
	@Query(value = "select * from role r inner join user_role ur on(r.role_id=ur.role_id) inner join user u on(u.user_id=ur.user_id) where u.email = :email",
			nativeQuery = true)	
		Role findRoleByUserEmail(@Param("email") String email);
	
}
