package org.learning.blogapplication.repositries;

import org.learning.blogapplication.entities.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
