package com.keepgoing.keepserver.domain.damage.payload.request;

import com.keepgoing.keepserver.domain.damage.enums.IssueType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DamageCreateRequest {
    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotNull(message = "Issue type cannot be blank")
    private IssueType issueType;

    private final String description;
}
