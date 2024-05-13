package com.keepgoing.keepserver.global.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class CustomResponseEntity<E> {
    @JsonIgnore
    private HttpStatus status;
    private int httpStatus;
    private E data;
    private String time;
    public CustomResponseEntity(HttpStatus status, E data) {
        this.status = status;
        this.httpStatus = this.status.value();
        this.data = data;
        this.time = LocalDateTime.now().toString();
    }

}
