package com.keepgoing.keepserver.domain.student.domain.repository.dto;

import com.keepgoing.keepserver.domain.user.domain.enums.Status;
import lombok.Builder;

@Builder
public record StudentResponseDto(
        long id,
        String studentName,
        String imgUrl,
        String studentId,
        String phoneNum,
        String mail,
        Status status
) {
}