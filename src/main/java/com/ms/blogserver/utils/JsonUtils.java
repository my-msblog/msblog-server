package com.ms.blogserver.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2022/2/5
 */
public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * List<T> 转 json 保存到数据库
     *
     * @param ts ts
     * @return {@link String}
     */
    public static <T> String toJson(List<T> ts) {
        return JSON.toJSONString(ts);
    }

    /**
     * 对象转json
     *
     * @param tResponse t
     * @return {@link String}
     */
    public static <T> String toJson(T tResponse){
        return JSONObject.toJSONString(tResponse);
    }

    /**
     * json 转 List<T>
     *
     * @param jsonString json字符串
     * @param clazz      clazz
     * @return {@link List}<{@link T}>
     */
    public static <T> List<T> toList(String jsonString, Class<T> clazz) {
        return JSONArray.parseArray(jsonString, clazz);
    }

    /**
     * 对象
     *
     * @param pojo   pojo
     * @param tClass t类
     * @return {@link T}
     */
    public static <T> T toObject(String pojo, Class<T> tClass) {
        try {
            return JSONObject.parseObject(pojo, tClass);
        } catch (Exception e) {
            log.error(tClass + "转 JSON 失败");
        }
        return null;
    }
}
