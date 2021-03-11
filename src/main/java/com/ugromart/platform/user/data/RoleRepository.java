package com.ugromart.platform.user.data;

import com.ugromart.platform.user.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
