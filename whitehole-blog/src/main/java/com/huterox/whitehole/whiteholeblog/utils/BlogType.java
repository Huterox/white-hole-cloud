package com.huterox.whitehole.whiteholeblog.utils;

import java.util.HashMap;

public class BlogType {

    public static HashMap<Integer,String> BLOGTYPE = new HashMap<>();
    static {
        BLOGTYPE.put(1,"原创");
        BLOGTYPE.put(2,"转载");
        BLOGTYPE.put(3,"翻译");
    }
}
