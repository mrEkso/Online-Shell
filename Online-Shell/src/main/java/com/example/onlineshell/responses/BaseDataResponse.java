package com.example.onlineshell.responses;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class BaseDataResponse {
    private int status;
    private String message;
    private Object data;

    public BaseDataResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
