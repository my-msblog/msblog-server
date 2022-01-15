package com.ms.blogserver.converter.bo;

import com.ms.blogserver.core.base.Converter;
import com.ms.blogserver.model.bo.ArticleBO;
import com.ms.blogserver.model.entity.Article;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
@org.mapstruct.Mapper
public interface ArticleBoConverter extends Converter<Article, ArticleBO> {

    ArticleBoConverter INSTANCE = Mappers.getMapper(ArticleBoConverter.class);
}
