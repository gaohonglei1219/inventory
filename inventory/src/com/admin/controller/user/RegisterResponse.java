package com.admin.controller.user;

public class RegisterResponse {

    private String code;
    
    private RegisterResponseMessage msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RegisterResponseMessage getMessage() {
        return msg;
    }

    public void setMessage(RegisterResponseMessage message) {
        this.msg = message;
    }
    
    
    
}
