package com.keepgoing.keepserver.domain.damage.mapper;

import com.keepgoing.keepserver.domain.damage.entity.Damage;
import com.keepgoing.keepserver.domain.damage.payload.request.DamageCreateRequest;
import com.keepgoing.keepserver.domain.damage.payload.response.DamageResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DamageMapper {

    @Mapping(source = "nfcCode", target = "code")
    DamageResponseDto entityToDto(Damage damage, String nfcCode);

    @Mapping(target = "reportDate", expression = "java(java.time.LocalDateTime.now())")
    Damage dtoToEntity(DamageCreateRequest damageCreateRequest);
}

