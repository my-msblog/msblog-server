package com.ms.blogserver.converter.vo;

import com.ms.blogserver.core.base.Converter;
import com.ms.blogserver.model.entity.Category;
import com.ms.blogserver.model.vo.CategoryVO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/12/15
 */
@org.mapstruct.Mapper
public interface CategoryVoConverter extends Converter<Category, CategoryVO> {
    CategoryVoConverter INSTANCE = Mappers.getMapper(CategoryVoConverter.class);
}
