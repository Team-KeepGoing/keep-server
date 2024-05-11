package com.keepgoing.keepserver.domain.user.entity.userroles;

import com.keepgoing.keepserver.domain.user.entity.role.Role;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Userroles {
    @Entity
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Table(name = "user_roles")
    public static class UserRoles {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "role_id")
        private Role role;
    }
}
