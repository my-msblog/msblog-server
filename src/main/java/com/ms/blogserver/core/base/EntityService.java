package com.ms.blogserver.core.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zhh
 * @time: 2022/6/27
 */
public interface EntityService<E, M extends BaseMapper<E>> extends IService<E>,BaseService {

    /**
     * 获取baseMapper
     *
     * @return {@link M}
     */
    @Override
    M getBaseMapper();

    /**
     * λ查询包装
     *
     * @return {@link LambdaQueryWrapper}<{@link E}>
     */
    LambdaQueryWrapper<E> lambdaQueryWrapper();

    /**
     * 查询列表
     *
     * @param wrapper   lambda的条件构造器
     * @param sFunction list中的元素
     * @return {@link List}<{@link A}>
     */
    default <A> List<A> simpleList(LambdaQueryWrapper<E> wrapper, SFunction<E, A> sFunction) {
        return SimpleQuery.list(wrapper, sFunction);
    }

    /**
     * 键映射表
     *
     * @param wrapper   lambda的条件构造器
     * @param sFunction 实体中属性的getter,用于封装后map中作为key的条件
     * @return {@link Map}<{@link A}, {@link E}>
     */
    default <A> Map<A, E> simpleKeyMap(LambdaQueryWrapper<E> wrapper, SFunction<E, A> sFunction) {
        return SimpleQuery.keyMap(wrapper, sFunction);
    }

    /**
     * map
     *
     * @param wrapper   lambda的条件构造器
     * @param keyFunc   封装后map中作为key的条件
     * @param valueFunc 封装后map中作为value的条件
     * @return {@link Map}<{@link A}, {@link P}>
     */
    default <A, P> Map<A, P> simpleMap(LambdaQueryWrapper<E> wrapper, SFunction<E, A> keyFunc, SFunction<E, P> valueFunc) {
        return SimpleQuery.map(wrapper, keyFunc, valueFunc);
    }
}
