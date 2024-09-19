package com.keepgoing.keepserver.global.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ExceptionResponseEntity extends ResponseEntity {
    private HttpStatus error;
    private String message;
}
