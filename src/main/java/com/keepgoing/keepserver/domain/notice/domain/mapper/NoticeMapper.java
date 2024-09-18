package com.keepgoing.keepserver.domain.notice.domain.mapper;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.Notice;
import com.keepgoing.keepserver.domain.notice.payload.req.NoticeCreateDto;
import com.keepgoing.keepserver.domain.notice.payload.res.NoticeResponseDto;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoticeMapper {
    @Mapping(source = "idx", target = "id")
    @Mapping(source = "teacher.name", target = "teacherName")
    NoticeResponseDto entityToDto(Notice notice);

    @Mapping(target = "teacher", source = "teacher")
    @Mapping(target = "isGlobal", source = "noticeCreateDto.isGlobal")
    @Mapping(target = "message", source = "noticeCreateDto.message")
    Notice toEntity(NoticeCreateDto noticeCreateDto, User teacher);

}
