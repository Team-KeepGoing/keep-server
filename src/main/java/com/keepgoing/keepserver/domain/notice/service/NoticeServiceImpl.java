package com.keepgoing.keepserver.domain.notice.service;

import com.keepgoing.keepserver.domain.notice.domain.entity.notice.Notice;
import com.keepgoing.keepserver.domain.notice.domain.entity.notice.NoticeReception;
import com.keepgoing.keepserver.domain.notice.domain.mapper.NoticeMapper;
import com.keepgoing.keepserver.domain.notice.domain.repository.NoticeReceptionRepository;
import com.keepgoing.keepserver.domain.notice.domain.repository.NoticeRepository;
import com.keepgoing.keepserver.domain.notice.payload.req.NoticeCreateDto;
import com.keepgoing.keepserver.domain.notice.payload.res.NoticeResponseDto;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import com.keepgoing.keepserver.domain.user.domain.repository.user.UserRepository;
import com.keepgoing.keepserver.domain.user.security.service.UserDetailsImpl;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.exception.notice.NoticeError;
import com.keepgoing.keepserver.global.exception.notice.NoticeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final NoticeReceptionRepository noticeReceptionRepository;
    private final NoticeMapper mapper;

    @Override
    public BaseResponse createNotice(NoticeCreateDto noticeCreateDto, Authentication authentication) {
        Notice notice = noticeRepository.save(
                Notice.builder()
                      .isGlobal(noticeCreateDto.isGlobal())
                      .teacher(getTeacher(authentication))
                      .message(noticeCreateDto.message()).build()
        );
        setReceptions(noticeCreateDto, notice);

        return new BaseResponse(HttpStatus.OK, "공지 등록 성공", mapper.entityToDto(notice));
    }

    @Override
    @Transactional
    public BaseResponse updateNotice(Long id, NoticeCreateDto noticeCreateDto, Authentication authentication) {
        User teacher = getTeacher(authentication);
        Notice notice = getNotice(id, teacher);

        notice.setGlobal(noticeCreateDto.isGlobal());
        if (noticeCreateDto.message() != null) {
            notice.setMessage(noticeCreateDto.message());
        }

        noticeRepository.save(notice);
        noticeReceptionRepository.deleteAllByNotice(notice);
        setReceptions(noticeCreateDto, notice);

        return new BaseResponse(HttpStatus.ACCEPTED, "공지 수정 성공", mapper.entityToDto(notice));
    }

    private void setReceptions(NoticeCreateDto noticeCreateDto, Notice notice) {
        List<NoticeReception> receptions = new ArrayList<>();
        List<User> users = noticeCreateDto.isGlobal()
                ? userRepository.findUsersByTeacherIs(false)
                : userRepository.findUsersByIdIn(noticeCreateDto.userIds());

        for (User user : users) {
            receptions.add(NoticeReception.builder().user(user).notice(notice).build());
        }

        noticeReceptionRepository.saveAll(receptions);
    }

    @Override
    @Transactional
    public BaseResponse deleteNotice(Long id, Authentication authentication) {
        noticeRepository.delete(getNotice(id, getTeacher(authentication)));
        return new BaseResponse(HttpStatus.OK, "공지가 삭제었습니다.");
    }

    @Override
    @Transactional
    public BaseResponse getNotice(Authentication authentication) {
        List<Notice> notices = noticeRepository.findAll();
        return new BaseResponse(HttpStatus.ACCEPTED, "전체 공지: ", getNoticeList(notices));
    }

    @Override
    @Transactional
    public BaseResponse getMyNotice(Authentication authentication) {
        User teacher = getTeacher(authentication);
        List<Notice> notices = noticeRepository.findNoticesByTeacher(teacher);
        getNoticeList(notices);
        return new BaseResponse(HttpStatus.OK, "내가 쓴 글 불러오기", getNoticeList(notices));
    }

    private void validateTeacher(User user) {
        if (!user.isTeacher()) {
            throw new NoticeException(NoticeError.USER_NOT_TEACHER);
        }
    }

    private void validateMyNotice(User user, Notice notice) {
        if (!Objects.equals(user.getName(), notice.getTeacher().getName())) {
            throw new NoticeException(NoticeError.USER_CANNOT_DELETE);
        }
    }

    private User getTeacher(Authentication authentication) {
        var ud = (UserDetailsImpl) authentication.getPrincipal();
        //noinspection OptionalGetWithoutIsPresent
        var teacher = userRepository.findById(ud.getId()).get();
        validateTeacher(teacher);
        return teacher;
    }

    private Notice getNotice(long id, User teacher) {
        Notice notice = noticeRepository.findNoticeByIdx(id);
        validateMyNotice(teacher, notice);
        return notice;
    }

    private List<NoticeResponseDto> getNoticeList(List<Notice> notices) {
        List<NoticeResponseDto> dtoList = new ArrayList<>();

        for (Notice n : notices) {
            dtoList.add(mapper.entityToDto(n));
        }
        return dtoList;
    }
}
