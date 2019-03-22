package com.authstr.ff.utils.web.controller;

public enum MsgEnum implements MsgEnumInterface {

    SUCCESS("1","请求成功!"),
    UNKNOWN_ERROR("-1","未知异常!"),
    PARA_ERROR("-100","参数错误!");


    String code=null;
    String message=null;
    private MsgEnum(String code, String message){
        this.code=code;
        this.message=message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
