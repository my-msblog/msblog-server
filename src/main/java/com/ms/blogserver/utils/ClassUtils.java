package com.ms.blogserver.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2022/2/3
 */
public class ClassUtils {


    /**
     * 转list
     *
     * @param obj   obj
     * @param clazz clazz
     * @return {@link List}<{@link T}>
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
