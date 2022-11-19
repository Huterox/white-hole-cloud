package com.huterox.whitehole.whiteholeuser.utils;

public class RedisTransKey {

    public static final String RedisNameSpace = "user";
    public static final String RedisTokenName = "token";
    public static final String RedisLoginName = "login";
    public static final String RedisEmailCodeName = "emailCode";
    public static final String RedisUserInfoEditorName = "userInfoEditor";
    public static final String RedisUserUpHeadImgName = "userUpHeadImg";
    public static final String RedisUserChangePassWordName = "userChangePassWord";
    public static final String RedisUserChangePassWordEmailName = "userChangePassWordEmail";
    public static final String RedisUserNoSuchAuthority = "userNoSuchAuthority";

    public static String setEmailKey(String key) {
        return RedisNameSpace + ":" + RedisEmailCodeName + ":" + key;
    }

    public static String setRootKey(String key) {
        return RedisNameSpace + ":" + key + ":";
    }

    public static String setTokenKey(String key) {
        return RedisNameSpace + ':' + RedisTokenName + ":" + key;
    }

    public static String setLoginKey(String key) {
        return RedisNameSpace + ':' + RedisLoginName + ":" + key;
    }

    public static String setUserInfoEditorKey(String key) {
        return RedisNameSpace + ':' + RedisUserInfoEditorName + ":" + key;
    }

    public static String setUserUpHeadImgKey(String key) {
        return RedisNameSpace + ':' + RedisUserUpHeadImgName + ":" + key;
    }

    public static String setUserChangePassWordKey(String key) {
        return RedisNameSpace + ':' + RedisUserChangePassWordName + ":" + key;
    }

    public static String setUserChangePassWordEmailKey(String key) {
        return RedisNameSpace + ':' + RedisUserChangePassWordEmailName + ":" + key;
    }

    public static String setUserNoSuchAuthority(String key) {
        return RedisNameSpace + ':' + RedisUserNoSuchAuthority + ":" + key;
    }

    public static String getEmailKey(String key) {
        return setEmailKey(key);
    }

    public static String getRootKey(String key) {
        return setRootKey(key);
    }

    public static String getTokenKey(String key) {
        return setTokenKey(key);
    }

    public static String getLoginKey(String key) {
        return setLoginKey(key);
    }

    public static String getUserInfoEditorKey(String key) {
        return setUserInfoEditorKey(key);
    }

    public static String getUserUpHeadImgKey(String key) {
        return setUserUpHeadImgKey(key);
    }

    public static String getUserChangePassWordKey(String key) {
        return setUserChangePassWordKey(key);
    }

    public static String getUserChangePassWordEmailKey(String key) {
        return setUserChangePassWordEmailKey(key);
    }

    public static String getUserNoSuchAuthority(String key) {
        return setUserNoSuchAuthority(key);
    }
}
