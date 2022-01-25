package com.ms.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ms.blogserver.model.entity.CommentLike;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/25
 */
@Mapper
public interface CommentLikeMapper extends BaseMapper<CommentLike> {
}
