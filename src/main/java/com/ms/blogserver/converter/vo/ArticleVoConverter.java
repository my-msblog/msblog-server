package com.ms.blogserver.converter.vo;

import com.ms.blogserver.core.base.Converter;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.vo.ArticleVO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/4
 */
@org.mapstruct.Mapper
public interface ArticleVoConverter extends Converter<Article, ArticleVO> {

    ArticleVoConverter INSTANCE = Mappers.getMapper(ArticleVoConverter.class);
}
