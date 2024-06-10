package com.keepgoing.keepserver.global.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ResponseEntity {
    protected int status;
    protected Object data;
}

