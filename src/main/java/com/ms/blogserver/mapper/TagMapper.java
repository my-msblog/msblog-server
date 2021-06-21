package com.ms.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ms.blogserver.model.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
}
