package com.keepgoing.keepserver.domain.notice.domain.repository;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.NoticeReception;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeReceptionRepository extends JpaRepository<NoticeReception, Long> {
}