package com.huterox.common.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**

 * FastJson工具类
 * 支持Object --> JSON(String)
 * 也支持 JSON(String) --> Object
 * 支持集合和JSON的变化
 * 支持自定义日期转化格式等
 * 这是一个静态的工具类
 */
public class FastJsonUtils {

    private static final SerializeConfig config;
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        config = new SerializeConfig();
        // 使用和json-lib兼容的日期输出格式
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
        config.put(java.sql.Timestamp.class, new JSONLibDataFormatSerializer());
    }

    private static final SerializerFeature[] features = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    /**
     * 将json转换成字符串(带序列化Feature)
     *
     * 输出空置字段
     *
     * list字段如果为null，输出为[]，而不是null
     *
     * 数值字段如果为null，输出为0，而不是null
     *
     * Boolean字段如果为null，输出为false，而不是null
     *
     * 字符类型字段如果为null，输出为""，而不是null
     *
     * @param object
     * @return
     */
    public static String toJsonWithFeatures(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 经json转换成字符串，按实际情况
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object, config);
    }


    /**
     * 将json转成object
     *
     * @param json
     * @return
     */
    public static Object fromJson(String json) {
        return JSON.parse(json);
    }

    /**
     * 将json转成对应的对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 按类型转化
     *
     * @param json
     * @param type 例： new TypeReference<Map<String, Object>>() {}
     * @return
     */
    public static <T> T fromJson(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type.getType());
    }

    /**
     * 将json转成数组
     *
     * @param json
     * @return
     */
    public static <T> Object[] toArray(String json) {
        return toArray(json, null);
    }

    /**
     * 转换为数组
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> Object[] toArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz).toArray();
    }

    /**
     * 转换为List
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /**
     * 将javabean转化为序列化的json字符串
     *
     * @param keyvalue
     * @return
     */
    public static Object beanToJson(Object keyvalue) {
        String textJson = JSON.toJSONString(keyvalue);
        Object objectJson = JSON.parse(textJson);
        return objectJson;
    }

    /**
     * 将string转化为序列化的json字符串
     *
     * @param json
     * @return
     */
    public static Object textToJson(String json) {
        Object objectJson = JSON.parse(json);
        return objectJson;
    }

    /**
     * json字符串转化为map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> stringToCollect(String json) {
        return JSONObject.parseObject(json);
    }

    /**
     * 指定时间格式 的json转换
     *
     * @param object
     * @param dateFormat
     * @return
     */
    public static String toJSONStringWithDateFormat(Object object, String dateFormat) {

        return JSON.toJSONStringWithDateFormat(object, dateFormat);
    }

    /**
     * 默认时间格式 的json转换
     *
     * @param object
     * @return
     */
    public static String toJSONStringWithDefaultDateFormat(Object object) {

        return JSON.toJSONStringWithDateFormat(object, DEFAULT_DATE_FORMAT);
    }
}