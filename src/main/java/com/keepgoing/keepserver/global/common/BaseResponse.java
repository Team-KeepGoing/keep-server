package com.keepgoing.keepserver.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse {

    private HttpStatus httpStatus;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public BaseResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public BaseResponse(HttpStatus httpStatus, String message, Object data){
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }
}