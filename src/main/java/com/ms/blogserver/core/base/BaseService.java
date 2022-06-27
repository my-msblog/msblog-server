package com.ms.blogserver.core.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

/**
 * @description:
 * @author: zhh
 * @time: 2022/6/27
 */
public interface BaseService {

    /**
     * LambdaQueryWrapper
     *
     * @param c c
     * @return {@link LambdaQueryWrapper}<{@link T}>
     */
    default <T> LambdaQueryWrapper<T> lambdaQueryWrapper(Class<T> c){
        return new LambdaQueryWrapper<>(c);
    }

    /**
     * LambdaUpdateWrapper
     *
     * @param c c
     * @return {@link LambdaUpdateWrapper}<{@link T}>
     */
    default <T> LambdaUpdateWrapper<T> lambadaUpdateWrapper(Class<T> c){
        return new LambdaUpdateWrapper<>(c);
    }
}
