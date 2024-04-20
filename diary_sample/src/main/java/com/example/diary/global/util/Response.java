package com.example.diary.global.util;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter @Builder
public class Response<T>{
    private HttpStatus httpStatus;
    private int statusNumber;
    private T data;

    public static <T> Response<?> result(T data, HttpStatus status) {
        return Response.builder()
                .httpStatus(status)
                .statusNumber(status.value())
                .data(data)
                .build();
    }

}
