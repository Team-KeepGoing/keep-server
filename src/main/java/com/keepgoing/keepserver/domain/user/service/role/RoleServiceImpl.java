package com.keepgoing.keepserver.domain.user.service.role;

import com.keepgoing.keepserver.domain.user.entity.role.Role;
import com.keepgoing.keepserver.domain.user.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Set<Role> getDefaultRole() {
        Set<Role> roles = new HashSet<>();
        return roles;
    }
}