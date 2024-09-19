package com.keepgoing.keepserver.domain.notice.service;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.Notice;
import com.keepgoing.keepserver.domain.notice.domain.entity.notice.NoticeReception;
import com.keepgoing.keepserver.domain.notice.domain.mapper.NoticeMapper;
import com.keepgoing.keepserver.domain.notice.domain.repository.NoticeReceptionRepository;
import com.keepgoing.keepserver.domain.notice.domain.repository.NoticeRepository;
import com.keepgoing.keepserver.domain.notice.payload.req.NoticeRequestDto;
import com.keepgoing.keepserver.domain.notice.payload.res.NoticeResponseDto;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import com.keepgoing.keepserver.domain.user.domain.repository.user.UserRepository;
import com.keepgoing.keepserver.domain.user.service.user.UserService;
import com.keepgoing.keepserver.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final NoticeReceptionRepository noticeReceptionRepository;
    private final NoticeMapper mapper;
    private final UserService userService;

    @Override
    @Transactional
    public BaseResponse createNotice(NoticeRequestDto noticeRequestDto, Authentication authentication) {
        Notice notice = noticeRepository.save(mapper.toEntity(noticeRequestDto, userService.getTeacher(authentication)));
        setReceptions(noticeRequestDto, notice);

        return new BaseResponse(HttpStatus.OK, "공지 등록 성공", mapper.entityToDto(notice));
    }

    @Override
    @Transactional
    public BaseResponse updateNotice(Long id, NoticeRequestDto noticeRequestDto, Authentication authentication) {
        User teacher = userService.getTeacher(authentication);
        Notice notice = noticeRepository.findNoticeByIdxAndTeacher_Id(id, teacher.getId());

        notice.setGlobal(noticeRequestDto.isGlobal());
        if (noticeRequestDto.message() != null) {
            notice.setMessage(noticeRequestDto.message());
        }

        noticeRepository.save(notice);
        noticeReceptionRepository.deleteAllByNotice(notice);
        setReceptions(noticeRequestDto, notice);

        return new BaseResponse(HttpStatus.ACCEPTED, "공지 수정 성공", mapper.entityToDto(notice));
    }

    private void setReceptions(NoticeRequestDto noticeRequestDto, Notice notice) {
        List<User> users = noticeRequestDto.isGlobal()
                ? userRepository.findUsersByTeacherIs(false)
                : userRepository.findUsersByIdIn(noticeRequestDto.userIds());

        List<NoticeReception> receptions = users.stream().map(user ->
            NoticeReception.builder()
                           .user(user)
                           .notice(notice)
                           .build()).collect(Collectors.toList());
        noticeReceptionRepository.saveAll(receptions);
    }

    @Override
    @Transactional
    public BaseResponse deleteNotice(Long id, Authentication authentication) {
        User teacher = userService.getTeacher(authentication);
        Notice notice = noticeRepository.findNoticeByIdxAndTeacher_Id(id, teacher.getId());
        noticeRepository.delete(notice);
        return new BaseResponse(HttpStatus.OK, "공지가 삭제었습니다.");
    }

    @Override
    public BaseResponse getNotice() {
        List<Notice> notices = noticeRepository.findAll();
        return new BaseResponse(HttpStatus.ACCEPTED, "전체 공지: ", getNoticeList(notices));
    }

    @Override
    public BaseResponse getNoticeOfTeacher(Authentication authentication) {
        User teacher = userService.getTeacher(authentication);
        List<Notice> notices = noticeRepository.findNoticesByTeacher(teacher);
        return new BaseResponse(HttpStatus.OK, "내가 쓴 글 불러오기", getNoticeList(notices));
    }

    private List<NoticeResponseDto> getNoticeList(List<Notice> notices) {
        return notices.stream().map(mapper :: entityToDto).collect(Collectors.toList());
    }
}
