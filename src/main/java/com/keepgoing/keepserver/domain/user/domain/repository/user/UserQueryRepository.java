package com.keepgoing.keepserver.domain.user.domain.repository.user;

import com.keepgoing.keepserver.domain.user.dto.UserDto;
import com.keepgoing.keepserver.domain.user.dto.UserNoticesDto;

public interface UserQueryRepository {
    UserDto getProfileById(long id);
    UserNoticesDto getNoticesById(long id);
}
