package com.keepgoing.keepserver.domain.user.payload.request;

import com.keepgoing.keepserver.domain.book.entity.dto.BookResponseDto;
import com.keepgoing.keepserver.domain.device.payload.response.DeviceResponseDto;
import com.keepgoing.keepserver.domain.user.entity.user.User;
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
    private List<BookResponseDto> brrowedBooks;
}