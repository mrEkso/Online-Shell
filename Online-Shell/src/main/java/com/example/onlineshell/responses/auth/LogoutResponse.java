package com.example.onlineshell.responses.auth;


import com.example.onlineshell.responses.BaseResponse;

public class LogoutResponse extends BaseResponse {
    public LogoutResponse() {
        super(200, "Ви успішно вийшли");
    }
}
