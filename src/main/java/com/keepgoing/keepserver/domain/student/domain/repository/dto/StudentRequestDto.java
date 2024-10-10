package com.keepgoing.keepserver.domain.student.domain.repository.dto;

public record StudentRequestDto(
        String studentName,
        String studentId,
        String phoneNum,
        String imgUrl,
        String address,
        String mail
) {
}