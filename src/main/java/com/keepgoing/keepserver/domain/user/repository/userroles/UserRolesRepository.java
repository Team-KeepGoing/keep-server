package com.keepgoing.keepserver.domain.user.repository.userroles;

import com.keepgoing.keepserver.domain.user.entity.userroles.Userroles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<Userroles.UserRoles, Long> {

}