package com.keepgoing.keepserver.domain.user.service.role;

import com.keepgoing.keepserver.domain.user.entity.role.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getDefaultRole();
}