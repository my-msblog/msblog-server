package com.ms.blogserver.converter.vo;

import com.ms.blogserver.converter.Converter;
import com.ms.blogserver.model.bo.CommentBO;
import com.ms.blogserver.model.vo.CommentVO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@org.mapstruct.Mapper
public interface CommentVoConverter extends Converter<CommentBO, CommentVO> {

    CommentVoConverter INSTANCE = Mappers.getMapper(CommentVoConverter.class);

}
