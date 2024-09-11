package com.keepgoing.keepserver.domain.notice.service;

import com.keepgoing.keepserver.domain.notice.domain.repository.NoticeRepository;
import com.keepgoing.keepserver.domain.notice.payload.req.NoticeCreateDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public BaseResponse createNotice(NoticeCreateDto noticeCreateDto, Authentication authentication) {
        return null;
    }

    @Override
    public BaseResponse updateNotice(Long id, NoticeCreateDto noticeCreateDto, Authentication authentication) {
        return null;
    }

    @Override
    public BaseResponse deleteNotice(Long id, Authentication authentication) {
        return null;
    }

    @Override
    public BaseResponse getNotice(Authentication authentication) {
        return null;
    }
}
