package com.example.onlineshell.responses.crud;


import com.example.onlineshell.responses.BaseResponse;

public class CreateResponse extends BaseResponse {
    public CreateResponse() {
        super(201, "Успішно створено");
    }
}
