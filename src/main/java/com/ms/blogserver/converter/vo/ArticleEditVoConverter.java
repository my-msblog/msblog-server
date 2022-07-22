package com.ms.blogserver.converter.vo;

import com.ms.blogserver.core.base.Converter;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.vo.ArticleEditVO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2022/7/21
 */
@org.mapstruct.Mapper
public interface ArticleEditVoConverter extends Converter<Article, ArticleEditVO> {

    ArticleEditVoConverter INSTANCE = Mappers.getMapper(ArticleEditVoConverter.class);
}
