package com.huterox.whitehole.whiteholeblog.exception;

/***
 * 错误码和错误信息定义类
 * 这里的错误码是20000开头的
 */
public enum BizCodeEnum {

    UNKNOW_EXCEPTION(20000,"系统未知异常"),
    VAILD_EXCEPTION(20001,"参数格式校验失败"),
    NO_SUCHUSER(20009,"该用户不存在"),
    BAD_BLOGINFO(20010,"文章摘要存在敏感信息"),
    BAD_CONTEXT(20011,"文章内容存在敏感信息"),
    OVER_UPBLOG(20012,"您已提交，休息一下十分钟后再来吧~"),
    NO_SUCHBLOG(20013,"不存在此博文"),
    NO_SUCHCONTENT(20014,"未找到对应内容"),
    CAN_NOT_FORK(20015,"操作过于频繁，请十分钟后操作"),
    THE_SOMEFORK(20015,"您不能fork自己的博文哟~"),
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
