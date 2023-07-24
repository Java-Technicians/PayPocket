package com.alkemy.paypocket.repositories;

import com.alkemy.paypocket.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByname(String rolName);
}
