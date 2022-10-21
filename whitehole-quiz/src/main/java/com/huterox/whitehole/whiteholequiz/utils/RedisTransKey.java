package com.huterox.whitehole.whiteholequiz.utils;

public class RedisTransKey {

    public static final String RedisUserNameSpace = "user";
    public static final String RedisNameSpace="quiz";
    public static final String RedisTokenName="token";
    public static final String RedisLoginName="login";
    public static final String RedisBaseUpQuizName = "baseQuizUp";
    public static final String RedisBaseUpAnsName = "baseAnsUp";



    public static String setRootKey(String key){
        return RedisNameSpace+":"+key+":";
    }
    public static String setTokenKey(String key){
        return RedisUserNameSpace+':'+RedisTokenName+":"+key;
    }
    public static String setLoginKey(String key){
        return RedisUserNameSpace+':'+RedisLoginName+":"+key;
    }
    public static String setBaseUpQuizKey(String key){return RedisNameSpace+':'+RedisBaseUpQuizName+":"+key;}
    public static String setBaseUpAnsKey(String  key){return RedisNameSpace+':'+RedisBaseUpAnsName+":"+key;}

    public static String getRootKey(String key){return setRootKey(key);}
    public static String getTokenKey(String key){return setTokenKey(key);}
    public static String getLoginKey(String key){return setLoginKey(key);}
    public static String getBaseUpQuizKey(String key){return setBaseUpQuizKey(key);}
    public static String getBaseUpAnsKey(String  key){return setBaseUpAnsKey(key);}
}
