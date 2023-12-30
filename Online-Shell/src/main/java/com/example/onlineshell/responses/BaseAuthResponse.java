package com.example.onlineshell.responses;

import lombok.Getter;

@Getter
public class BaseAuthResponse extends BaseResponse {
    private final Object data;
    private final String token;

    public BaseAuthResponse(int status, String message, Object data, String token) {
        super(status, message);
        this.data = data;
        this.token = token;
    }
}
