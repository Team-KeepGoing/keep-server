package com.keepgoing.keepserver.domain.notice.domain.repository;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Notice findNoticeByIdx(long idx);
    List<Notice> findNoticesByTeacher_Name(String name);
}
