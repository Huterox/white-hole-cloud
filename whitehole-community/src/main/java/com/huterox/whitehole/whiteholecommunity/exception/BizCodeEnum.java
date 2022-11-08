package com.huterox.whitehole.whiteholecommunity.exception;

/***
 * 错误码和错误信息定义类
 * 这里的错误码是40000开头的
 */
public enum BizCodeEnum {
    UNKNOW_EXCEPTION(40000,"系统未知异常"),
    VAILD_EXCEPTION(40001,"参数格式校验失败"),
    NO_SUCHUSER(40002,"该用户不存在"),
    NO_SUCHCOMMUNITY(40003,"不存在该社区"),
    OVER_UPCOMMUNITY(40004,"您已提交，休息一下十分钟后再来吧~"),
    BAD_COMMUNITY_UP(40005,"存在敏感信息请十分钟后重试"),
    OVER_REQUEST(40006,"休息一下吧，您已向该社区创建者提交申请哟~"),
    TOO_FASTAUTHORITY(40007,"您的审核频率过快！"),
    BAD_BLOGINFO(40010,"文章摘要存在敏感信息"),
    BAD_CONTEXT(40011,"文章内容存在敏感信息"),
    OVER_UPBLOG(40012,"休息一分钟再来吧！"),
    BAD_AUTHORITY(40007,"非法越权操作"),
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
