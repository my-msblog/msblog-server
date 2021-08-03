package com.ms.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ms.blogserver.model.entity.Favorites;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoritesMapper extends BaseMapper<Favorites> {
}
