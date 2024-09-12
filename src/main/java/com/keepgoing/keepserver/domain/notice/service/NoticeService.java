package com.keepgoing.keepserver.domain.notice.service;

import com.keepgoing.keepserver.domain.notice.payload.req.NoticeCreateDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.security.core.Authentication;

public interface NoticeService {
    BaseResponse createNotice(NoticeCreateDto noticeCreateDto, Authentication authentication);
    BaseResponse updateNotice(Long id, NoticeCreateDto noticeCreateDto, Authentication authentication);
    BaseResponse deleteNotice(Long id, Authentication authentication);
    BaseResponse getNotice(Authentication authentication);
    BaseResponse getMyNotice(Authentication authentication);
}
