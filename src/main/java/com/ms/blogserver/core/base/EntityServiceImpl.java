package com.ms.blogserver.core.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @description:
 * @author: zhh
 * @time: 2022/6/27
 */
public class EntityServiceImpl<E, M extends BaseMapper<E>> extends ServiceImpl<M, E> implements EntityService<E, M>{

    @Override
    public M getBaseMapper() {
        return super.getBaseMapper();
    }

    /**
     * λ查询包装
     *
     * @return {@link LambdaQueryWrapper}<{@link E}>
     */
    @Override
    public LambdaQueryWrapper<E> lambdaQueryWrapper() {
        return new LambdaQueryWrapper<>();
    }
}
