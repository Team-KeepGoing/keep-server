package com.keepgoing.keepserver.domain.notice.domain.repository;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.Notice;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Notice findNoticeByIdxAndTeacher_Id(long idx, long tIdx);
    List<Notice> findNoticesByTeacher(User teacher);
}
