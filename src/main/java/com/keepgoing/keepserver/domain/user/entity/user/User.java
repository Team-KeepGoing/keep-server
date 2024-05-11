package com.keepgoing.keepserver.domain.user.entity.user;

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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    /*
        교사 여부
    */
    @Column(nullable = false)
    private boolean teacher;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Userroles.UserRoles> roles = new HashSet<>();

    public void hidePassword(String password) {
        this.password = password;
    }

    public static User registerUser(
            String email,
            String password,
            String name,
            boolean teacher
    ) {
        User user = new User();
        user.email = email;
        user.password = password;
        user.name = name;
        user.teacher = teacher;

//        for (Role role : roles) {
//            Userroles.UserRoles userRoles = Userroles.UserRoles.createUserRoles(user, role);
//            user.getRoles().add(userRoles);
//        }
        return user;
    }

    public void fixUserData(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
