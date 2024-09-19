package com.keepgoing.keepserver.domain.user.domain.repository.user;

import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserQueryRepository {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<User> findByEmailEquals(String email);
    List<User> findUsersByIdIn(List<Long> ids);
    List<User> findUsersByTeacherIs(boolean isTeacher);
    Optional<User> findByIdAndTeacherIsTrue(Long id);
}