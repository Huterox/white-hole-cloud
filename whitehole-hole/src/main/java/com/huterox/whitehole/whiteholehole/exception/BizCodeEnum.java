package com.huterox.whitehole.whiteholehole.exception;

/***
 * 错误码和错误信息定义类
 * 这里的错误码是50000开头的
 */
public enum BizCodeEnum {
    UNKNOW_EXCEPTION(50000,"系统未知异常"),
    VAILD_EXCEPTION(50001,"参数格式校验失败"),
    UP_DIARY_OVER(50003,"提交日志的时间为3个小时哟"),
    NO_SUCHUSER(50002,"该用户不存在"),
    BAD_LOGIN_PARAMS(50012,"请求异常！触发5次以上账号将保护性封禁"),
    BAD_TOKEN(50014,"token校验失败"),
    NOT_LOGIN(50011,"用户未登录"),
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
