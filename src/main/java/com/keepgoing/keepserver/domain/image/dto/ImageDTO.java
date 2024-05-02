package com.keepgoing.keepserver.domain.image.dto;

import lombok.Builder;

@Builder
public record ImageDTO(
        String imgUrl
) {
}