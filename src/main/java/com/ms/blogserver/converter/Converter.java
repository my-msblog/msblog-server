package com.ms.blogserver.converter;

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
public interface Converter<T extends Serializable,R> {

    /**
     * T 转化 R
     * @param t
     * @return
     */
    R toData(T t);
    /**
     * R 转化 T
     * @param r
     * @return
     */
    T fromData(R r);

    /**
     * list 转化 R
     * @param tList
     * @return
     */
    List<R> toDataList(List<T> tList);

    /**
     * list 转化 T
     * @param rList
     * @return
     */
    List<T> fromDataList(List<R> rList);

    /**
     * 非空转化 t -> r
     * @param t
     * @param r
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toDataNoNull(T t, @MappingTarget R r);

    /**
     * 非空转化 r -> t
     * @param t
     * @param r
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromDataNoNull(R r, @MappingTarget T t);

    /**
     * list非空转化 t -> r
     * @param tList
     * @param rList
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toDataListNoNull(List<T> tList,@MappingTarget List<R> rList);

    /**
     * list非空转化 r -> t
     * @param tList
     * @param rList
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromDataListNoNull(List<R> rList,@MappingTarget List<T> tList);
}
