package com.keepgoing.keepserver.domain.user.domain.repository.user;

import com.keepgoing.keepserver.domain.book.payload.response.QBookResponseDto;
import com.keepgoing.keepserver.domain.device.payload.response.QDeviceResponseDto;
import com.keepgoing.keepserver.domain.notice.payload.res.QNoticeResponseDto;
import com.keepgoing.keepserver.domain.user.domain.entity.user.QUser;
import com.keepgoing.keepserver.domain.user.dto.QUserDto;
import com.keepgoing.keepserver.domain.user.dto.UserDto;
import com.keepgoing.keepserver.domain.user.dto.UserNoticesDto;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

import static com.keepgoing.keepserver.domain.book.domain.entity.QBook.book;
import static com.keepgoing.keepserver.domain.device.domain.entity.QDevice.device;
import static com.keepgoing.keepserver.domain.student.domain.entity.QStudent.student;
import static com.keepgoing.keepserver.domain.user.domain.entity.user.QUser.user;
import static com.keepgoing.keepserver.domain.notice.domain.entity.notice.QNotice.notice;
import static com.keepgoing.keepserver.domain.notice.domain.entity.notice.QNoticeReception.noticeReception;

@RequiredArgsConstructor
public class UserQueryRepositoryImpl implements UserQueryRepository {
    private final JPAQueryFactory factory;

    @Override
    public UserDto getProfileById(long id) {
        var map = factory
                .select(user)
                .from(user)
                .leftJoin(device).on(device.borrower.id.eq(user.id))
                .leftJoin(book).on(book.borrower.id.eq(user.id))
                .leftJoin(student).on(student.mail.eq(user.email))
                .where(user.id.eq(id))
                .distinct()
                .transform(
                        GroupBy.groupBy(user.id).as(new QUserDto(
                                user.id,
                                user.email,
                                student.studentId,
                                user.name,
                                user.teacher,
                                GroupBy.set(
                                        new QDeviceResponseDto(
                                                device.id,
                                                device.deviceName,
                                                device.imgUrl,
                                                device.borrower.name,
                                                device.lastBorrowerMail,
                                                device.regDate,
                                                device.rentDate,
                                                device.status
                                        )
                                ),
                                GroupBy.set(
                                        new QBookResponseDto(
                                                book.id,
                                                book.bookName,
                                                book.writer,
                                                book.imageUrl,
                                                book.nfcCode,
                                                book.lastBorrowerMail,
                                                book.registrationDate,
                                                book.rentDate,
                                                book.state
                                        )
                                )
                        ))
                );

        var dto = map.get(id);
        return UserDto.builder()
                      .id(dto.id())
                      .name(dto.name())
                      .studentId(dto.studentId())
                      .email(dto.email())
                      .teacher(dto.teacher())
                      .borrowedDevices(dto.borrowedDevices()
                                          .stream()
                                          .filter(r -> r.id() != 0)
                                          .collect(Collectors.toSet()))
                      .borrowedBooks(dto.borrowedBooks().stream().filter(r -> r.id() != 0).collect(Collectors.toSet()))
                      .build();
//        return null;
    }

    @Override
    public UserNoticesDto getNoticesById(long id) {
        var teacher = new QUser("teacher");
        var map = factory
                .select(
                        new QNoticeResponseDto(
                                notice.idx,
                                notice.message,
                                teacher.name,
                                notice.createTime,
                                notice.editTime
                        )
                )
                .from(user)
                .leftJoin(noticeReception).on(noticeReception.user.id.eq(user.id))
                .leftJoin(noticeReception.notice, notice)
                .leftJoin(notice.teacher, teacher)
                .where(user.id.eq(id))
                .distinct()
                .orderBy(notice.editTime.desc())
                .fetch();
        return UserNoticesDto.builder()
                             .notices(map)
                             .build();
    }
}
