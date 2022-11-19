package com.huterox.whitehole.whiteholehole.utils;

public class RedisTransKey {

    public static final String RedisNameSpace="user";
    public static final String RedisTokenName="token";
    public static final String RedisLoginName="login";
    public static final String RedisNameSpaceRoot="hole";
    public static final String RedisDiaryUp = "diaryUp";


    public static String setRootKey(String key){
        return RedisNameSpaceRoot+":"+key+":";
    }
    public static String setTokenKey(String key){
        return RedisNameSpace+':'+RedisTokenName+":"+key;
    }
    public static String setLoginKey(String key){
        return RedisNameSpace+':'+RedisLoginName+":"+key;
    }
    public static String setDiaryUpKey(String key){return RedisNameSpaceRoot+":"+RedisDiaryUp+":"+key;}

    public static String getRootKey(String key){return setRootKey(key);}
    public static String getTokenKey(String key){return setTokenKey(key);}
    public static String getLoginKey(String key){return setLoginKey(key);}
    public static String getDiaryUpKey(String key){return setDiaryUpKey(key);}

}
