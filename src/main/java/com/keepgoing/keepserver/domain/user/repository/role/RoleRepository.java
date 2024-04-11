package com.keepgoing.keepserver.domain.user.repository.role;

import com.keepgoing.keepserver.domain.user.entity.role.Role;
import com.keepgoing.keepserver.domain.user.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}