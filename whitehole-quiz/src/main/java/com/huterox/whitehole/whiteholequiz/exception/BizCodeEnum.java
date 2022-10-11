package com.huterox.whitehole.whiteholequiz.exception;

/***
 * 错误码和错误信息定义类
 * 这里的错误码是30000开头的
 */
public enum BizCodeEnum {
    UNKNOW_EXCEPTION(30000,"系统未知异常"),
    VAILD_EXCEPTION(30001,"参数格式校验失败"),
    SUCCESSFUL(200,"successful");

    private int code;
    private String msg;
    BizCodeEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
