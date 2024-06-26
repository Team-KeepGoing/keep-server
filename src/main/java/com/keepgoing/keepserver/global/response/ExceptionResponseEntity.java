package com.keepgoing.keepserver.global.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Data
@SuperBuilder
public class ExceptionResponseEntity extends ResponseEntity {
    private HttpStatus error;
    private String message;
}
