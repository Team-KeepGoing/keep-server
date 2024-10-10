package com.keepgoing.keepserver.domain.damage.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DamageCreateRequest {
    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotBlank(message = "Issue type cannot be blank")
    private String issueType;

    @NotBlank(message = "Description cannot be blank")
    private final String description;

}