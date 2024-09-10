package com.keepgoing.keepserver.domain.user.domain.repository.user;

import com.keepgoing.keepserver.domain.user.dto.UserDto;

public interface UserQueryRepository {
    UserDto getProfileByEmail(long id);
}
