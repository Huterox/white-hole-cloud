package com.huterox.whitehole.whiteholeblog.utils;

public class RedisTransKey {

    public static final String RedisUserNameSpace = "user";
    public static final String RedisNameSpace="blog";
    public static final String RedisTokenName="token";
    public static final String RedisLoginName="login";
    public static final String RedisBlogUpName="blogUp";
    public static final String RedisForkBlogName = "forkBlog";


    public static String setRootKey(String key){
        return RedisNameSpace+":"+key+":";
    }
    public static String setTokenKey(String key){
        return RedisUserNameSpace+':'+RedisTokenName+":"+key;
    }
    public static String setLoginKey(String key){
        return RedisUserNameSpace+':'+RedisLoginName+":"+key;
    }
    public static String setBlogUpKey(String key){return RedisNameSpace+':'+RedisBlogUpName+":"+key;}
    public static String setForkBlogKey(String key){return RedisNameSpace+':'+RedisForkBlogName+":"+key;}

    public static String getRootKey(String key){return setRootKey(key);}
    public static String getTokenKey(String key){return setTokenKey(key);}
    public static String getLoginKey(String key){return setLoginKey(key);}
    public static String getBlogUpKey(String key){return setBlogUpKey(key);}
    public static String getForkBlogKey(String key){return setForkBlogKey(key);}

}
