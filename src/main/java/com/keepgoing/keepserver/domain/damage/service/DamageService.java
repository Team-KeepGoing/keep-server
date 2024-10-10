package com.keepgoing.keepserver.domain.damage.service;

import com.keepgoing.keepserver.domain.damage.payload.request.DamageCreateRequest;
import com.keepgoing.keepserver.global.common.BaseResponse;

public interface DamageService {

    BaseResponse reportDamage(DamageCreateRequest request);

    BaseResponse getAllDamages();

    BaseResponse deleteDamage(Long id);
}
