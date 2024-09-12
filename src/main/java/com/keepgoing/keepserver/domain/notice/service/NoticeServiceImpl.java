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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final NoticeReceptionRepository noticeReceptionRepository;
    private final NoticeMapper mapper;

    @Override
    public BaseResponse createNotice(NoticeCreateDto noticeCreateDto, Authentication authentication) {
        var ud = (UserDetailsImpl) authentication.getPrincipal();
        // noinspection OptionalGetWithoutIsPresent
        var teacher = userRepository.findById(ud.getId()).get(); // already checked
        validateTeacher(teacher);
        Notice notice = noticeRepository.save(
                Notice.builder()
                        .isGlobal(noticeCreateDto.isGlobal())
                        .teacher(teacher)
                        .message(noticeCreateDto.getMessage())
                        .build()
        );

        List<NoticeReception> receptions = new ArrayList<>();
        List<User> users = noticeCreateDto.isGlobal() ?
                userRepository.findUsersByTeacherIs(false) :
                userRepository.findUsersByIdIn(noticeCreateDto.getUserIds());
        for (User user : users) {
            receptions.add(
                    NoticeReception
                            .builder()
                            .user(user)
                            .notice(notice)
                            .build()
            );
        }

        noticeReceptionRepository.saveAll(receptions);

        return new BaseResponse(HttpStatus.ACCEPTED, "공지 등록 성공", mapper.entityToDto(notice));
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
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeResponseDto> dtoList = new ArrayList<>();

        for (Notice n : notices) {
            dtoList.add(mapper.entityToDto(n));
        }
        return new BaseResponse(HttpStatus.ACCEPTED, "전체 공지: ", dtoList);
    }

    private void validateTeacher(User user) {
        if (!user.isTeacher()) {
            throw new NoticeException(NoticeError.USER_NOT_TEACHER);
        }
    }

}
