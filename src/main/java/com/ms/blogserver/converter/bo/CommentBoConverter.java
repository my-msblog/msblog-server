package com.ms.blogserver.converter.bo;

import com.ms.blogserver.converter.Converter;
import com.ms.blogserver.model.entity.Comment;
import com.ms.blogserver.model.bo.CommentBO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@org.mapstruct.Mapper
public interface CommentBoConverter extends Converter<Comment,CommentBO> {

    CommentBoConverter INSTANCE = Mappers.getMapper(CommentBoConverter.class);
}
