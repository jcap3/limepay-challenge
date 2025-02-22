package com.example.limepay.model;

import com.example.limepay.error.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private String message;

    private String code;

    private T body;
    
    private ErrorCode errorCode;
}