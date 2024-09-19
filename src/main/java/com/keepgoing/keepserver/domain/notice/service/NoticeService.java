package com.keepgoing.keepserver.domain.notice.service;

import com.keepgoing.keepserver.domain.notice.payload.req.NoticeRequestDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.security.core.Authentication;

public interface NoticeService {
    BaseResponse createNotice(NoticeRequestDto noticeRequestDto, Authentication authentication);
    BaseResponse updateNotice(Long id, NoticeRequestDto noticeRequestDto, Authentication authentication);
    BaseResponse deleteNotice(Long id, Authentication authentication);
    BaseResponse getNotice(Authentication authentication);
    BaseResponse getNoticeOfTeacher(Authentication authentication);
}
