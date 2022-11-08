package com.huterox.whitehole.whiteholecommunity.utils;

public class RedisTransKey {

    public static final String RedisNameSpace="user";
    public static final String RedisTokenName="token";
    public static final String RedisLoginName="login";
    public static final String RedisNameSpaceRoot="community";
    public static final String RedisUpCommunity = "upCommunity";
    public static final String RedisJoinCommunity = "joinCommunity";
    public static final String RedisAcBadAuthority = "AcAuthority";
    public static final String RedisCommunityUpBlog = "communityUpBlog";

    public static String setRootKey(String key){
        return RedisNameSpaceRoot+":"+key+":";
    }
    public static String setTokenKey(String key){
        return RedisNameSpace+':'+RedisTokenName+":"+key;
    }
    public static String setLoginKey(String key){
        return RedisNameSpace+':'+RedisLoginName+":"+key;
    }
    public static String setUpCommunityKey(String key){return RedisNameSpaceRoot+':'+RedisUpCommunity+":"+key;}
    public static String setJoinCommunityKey(String key){return RedisNameSpaceRoot+':'+RedisJoinCommunity+":"+key;}
    public static String setAcAuthority(String key){return RedisNameSpaceRoot+':'+RedisAcBadAuthority+":"+key;}
    public static String setCommunityUpBlog(String key){return RedisNameSpaceRoot+':'+RedisCommunityUpBlog+":"+key;}

    public static String getRootKey(String key){return setRootKey(key);}
    public static String getTokenKey(String key){return setTokenKey(key);}
    public static String getLoginKey(String key){return setLoginKey(key);}
    public static String getUpCommunityKey(String key){return setUpCommunityKey(key);}
    public static String getRedisJoinCommunity(String key){return setJoinCommunityKey(key);}
    public static String getAcAuthority(String key){return setAcAuthority(key);}
    public static String getCommunityUpBlog(String key){return setCommunityUpBlog(key);}

}
