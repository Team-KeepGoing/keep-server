package com.keepgoing.keepserver.domain.damage.mapper;

import com.keepgoing.keepserver.domain.damage.entity.Damage;
import com.keepgoing.keepserver.domain.damage.payload.request.DamageCreateRequest;
import com.keepgoing.keepserver.domain.damage.payload.response.DamageResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DamageMapper {
    public DamageResponseDto entityToDto(Damage entity) {
        return DamageResponseDto.builder()
                                .id(entity.getId())
                                .code(entity.getCode())
                                .issueType(entity.getIssueType())
                                .description(entity.getDescription())
                                .reportDate(entity.getReportDate())
                                .build();
    }

    public Damage dtoToEntity(DamageCreateRequest dto) {
        return Damage.builder()
                     .code(dto.getCode())
                     .issueType(dto.getIssueType())
                     .description(dto.getDescription())
                     .reportDate(LocalDateTime.now())
                     .build();
    }
}
