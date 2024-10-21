package com.keepgoing.keepserver.domain.user.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String email;

    private final String name;

    @JsonIgnore // 이 어노테이션은 JSON으로 변환될 때, password를 제외시킴
    private final String password;

    @Getter
    private final boolean teacher;

    private final boolean approved;

    public UserDetailsImpl(Long id, String email, String name, String password, boolean teacher, boolean approved) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.teacher = teacher;
        this.approved = approved;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword(),
                user.isTeacher(),
                user.isApproved()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

}
