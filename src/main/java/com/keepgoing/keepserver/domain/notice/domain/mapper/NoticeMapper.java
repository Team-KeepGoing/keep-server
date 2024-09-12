package com.keepgoing.keepserver.domain.notice.domain.mapper;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.Notice;
import com.keepgoing.keepserver.domain.notice.payload.res.NoticeResponseDto;
import org.springframework.stereotype.Component;

@Component
public class NoticeMapper {
    public NoticeResponseDto entityToDto(Notice notice){
        return NoticeResponseDto.builder()
                .id(notice.getIdx())
                .message(notice.getMessage())
                .createTime(notice.getCreateTime())
                .editTime(notice.getEditTime())
                .teacherName(notice.getTeacher().getName())
                .build();
    }
}
