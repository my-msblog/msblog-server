package com.ms.blogserver.converter.vo;

import com.ms.blogserver.converter.Converter;
import com.ms.blogserver.model.entity.Tag;
import com.ms.blogserver.model.vo.TagVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
@org.mapstruct.Mapper
public interface TagVoConverter extends Converter<Tag, TagVO> {

    TagVoConverter INSTANCE = Mappers.getMapper(TagVoConverter.class);
}
