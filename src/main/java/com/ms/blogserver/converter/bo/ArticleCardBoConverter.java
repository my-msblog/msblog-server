package com.ms.blogserver.converter.bo;

import com.ms.blogserver.converter.Converter;
import com.ms.blogserver.model.bo.ArticleCardBO;
import com.ms.blogserver.model.entity.Article;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
@org.mapstruct.Mapper
public interface ArticleCardBoConverter extends Converter<Article, ArticleCardBO> {

    ArticleCardBoConverter INSTANCE = Mappers.getMapper(ArticleCardBoConverter.class);
}
