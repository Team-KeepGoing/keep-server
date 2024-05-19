package com.keepgoing.keepserver.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse {

    private HttpStatus httpStatus;
    private String massage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public BaseResponse(HttpStatus httpStatus, String massage) {
        this.httpStatus = httpStatus;
        this.massage = massage;
    }

    public BaseResponse(HttpStatus httpStatus, String massage, Object data){
        this.httpStatus = httpStatus;
        this.massage = massage;
        this.data = data;
    }
}