package com.keepgoing.keepserver.domain.notice.service;

import com.keepgoing.keepserver.domain.notice.payload.req.noticeCreateDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import org.springframework.security.core.Authentication;

public interface NoticeService {
    BaseResponse createNotice(noticeCreateDto noticeCreateDto, Authentication authentication);
    BaseResponse updateNotice(Long id, noticeCreateDto noticeCreateDto, Authentication authentication);
    BaseResponse deleteNotice(Long id, Authentication authentication);
    BaseResponse getNotice(Authentication authentication);
}
