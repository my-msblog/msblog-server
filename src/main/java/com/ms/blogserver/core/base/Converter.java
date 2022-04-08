package com.ms.blogserver.core.base;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.io.Serializable;
import java.util.List;

/**
 * @description: Converter转换接口
 * @author: zhh
 * @time: 2021/5/31
 */
public interface Converter<T extends Serializable, R> {

    /**
     * T 转化 R
     *
     * @param t t
     * @return {@link R}
     */
    R toData(T t);

    /**
     * R 转化 T
     *
     * @param r r
     * @return {@link T}
     */
    T fromData(R r);

    /**
     * list 转化 R
     *
     * @param tList t列表
     * @return {@link List}<{@link R}>
     */
    List<R> toDataList(List<T> tList);

    /**
     * list 转化 T
     *
     * @param rList r列表
     * @return {@link List}<{@link T}>
     */
    List<T> fromDataList(List<R> rList);

    /**
     * 非空转化 t -> r
     *
     * @param t t
     * @param r r
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toDataNoNull(T t, @MappingTarget R r);

    /**
     * 非空转化 r -> t
     *
     * @param r r
     * @param t t
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromDataNoNull(R r, @MappingTarget T t);

    /**
     * list非空转化 t -> r
     *
     * @param tList t列表
     * @param rList r列表
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toDataListNoNull(List<T> tList, @MappingTarget List<R> rList);

    /**
     * 从数据列表不为空
     * list非空转化 r -> t
     *
     * @param rList r列表
     * @param tList t列表
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromDataListNoNull(List<R> rList, @MappingTarget List<T> tList);
}
