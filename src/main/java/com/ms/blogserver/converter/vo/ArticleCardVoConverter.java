package com.ms.blogserver.converter.vo;

import com.ms.blogserver.converter.Converter;
import com.ms.blogserver.model.bo.ArticleCardBO;
import com.ms.blogserver.model.vo.ArticleCardVO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
@org.mapstruct.Mapper
public interface ArticleCardVoConverter extends Converter<ArticleCardBO, ArticleCardVO> {

    ArticleCardVoConverter INSTANCE = Mappers.getMapper(ArticleCardVoConverter.class);
}
