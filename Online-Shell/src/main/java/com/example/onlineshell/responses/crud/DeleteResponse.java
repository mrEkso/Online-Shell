package com.example.onlineshell.responses.crud;


import com.example.onlineshell.responses.BaseResponse;

public class DeleteResponse extends BaseResponse {
    public DeleteResponse() {
        super(204, "Успішно видалено");
    }
}
