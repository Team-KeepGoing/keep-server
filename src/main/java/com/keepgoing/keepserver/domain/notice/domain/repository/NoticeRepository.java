package com.keepgoing.keepserver.domain.notice.domain.repository;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
