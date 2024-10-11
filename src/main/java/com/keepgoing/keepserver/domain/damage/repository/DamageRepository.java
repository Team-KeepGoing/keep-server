package com.keepgoing.keepserver.domain.damage.repository;

import com.keepgoing.keepserver.domain.damage.entity.Damage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamageRepository extends JpaRepository<Damage, Long> {
    boolean existsByCodeAndIssueType(String code, String issueType);
}
