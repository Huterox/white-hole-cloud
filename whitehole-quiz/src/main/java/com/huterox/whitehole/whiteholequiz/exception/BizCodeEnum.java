package com.huterox.whitehole.whiteholequiz.exception;

/***
 * 错误码和错误信息定义类
 * 这里的错误码是30000开头的
 */
public enum BizCodeEnum {
    UNKNOW_EXCEPTION(30000,"系统未知异常"),
    VAILD_EXCEPTION(30001,"参数格式校验失败"),
    NO_SUCHUSER(30009,"该用户不存在"),
    BAD_PUTDATA(30010,"信息提交错误，请重新检查"),
    NOT_LOGIN(30011,"用户未登录"),
    BAD_LOGIN_PARAMS(30012,"请求异常！触发5次以上账号将保护性封禁"),
    NUNKNOW_LGINTYPE(30013,"平台识别异常"),
    BAD_TOKEN(30014,"token校验失败"),
    HAS_UPQUIZ(30010,"提问次数过于频繁，歇息一分钟吧"),
    HAS_UPANS(30011,"回答次数过去频繁，歇息五分钟吧"),
    OVER_SENSITIVE_WORDS(30011,"内容不适宜发布，铭感词汇较多"),
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
