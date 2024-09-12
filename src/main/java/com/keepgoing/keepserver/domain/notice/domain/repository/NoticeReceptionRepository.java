package com.keepgoing.keepserver.domain.notice.domain.repository;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.Notice;
import com.keepgoing.keepserver.domain.notice.domain.entity.notice.NoticeReception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeReceptionRepository extends JpaRepository<NoticeReception, Long> {
    void deleteByNotice(Notice notice);
}