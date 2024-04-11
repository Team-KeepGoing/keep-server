package com.keepgoing.keepserver.domain.user.entity.user;

import com.keepgoing.keepserver.domain.user.entity.role.Role;
import com.keepgoing.keepserver.domain.user.entity.userroles.Userroles;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*
        이메일
    */
    @Column(nullable = false)
    private String email;

    /*
        패스워드
    */
    @Column(nullable = false)
    private String password;

    /*
        이름
    */
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Userroles.UserRoles> roles = new HashSet<>();

    public void hidePassword(String password) {
        this.password = password;
    }

    public static User registerUser(
            String email,
            String password,
            String name,
            Set<Role> roles
    ) {
        User user = new User();
        user.email = email;
        user.password = password;
        user.name = name;

        for (Role role : roles) {
            Userroles.UserRoles userRoles = Userroles.UserRoles.createUserRoles(user, role);
            user.getRoles().add(userRoles);
        }
        return user;
    }
}
