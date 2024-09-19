package com.keepgoing.keepserver.domain.user.dto;

import com.keepgoing.keepserver.domain.book.payload.response.BookResponseDto;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.user.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserProfileDto {
    private User user;
    private List<DeviceResponseDto> borrowedDevices;
    private List<BookResponseDto> borrowedBooks;
}