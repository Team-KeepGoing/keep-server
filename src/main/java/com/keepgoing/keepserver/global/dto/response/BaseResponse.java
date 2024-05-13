package com.keepgoing.keepserver.global.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse {

    private HttpStatus httpStatus;
    private String massage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public BaseResponse() {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.massage = "잘못된 반환입니다.";
        this.data = null;
    }
    public BaseResponse(HttpStatus httpStatus, String massage) {
        this.httpStatus = httpStatus;
        this.massage = massage;
    }
    public void of(HttpStatus httpStatus, String massage, Object data){
        this.httpStatus = httpStatus;
        this.massage = massage;
        this.data = data;
    }
}