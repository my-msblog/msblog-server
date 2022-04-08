package com.ms.blogserver.converter.vo;

import com.ms.blogserver.core.base.Converter;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.vo.ArticleRecommendVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2022/4/8
 */
@Mapper
public interface RecommendVoConverter extends Converter<Article, ArticleRecommendVO> {

    RecommendVoConverter INSTANCE = Mappers.getMapper(RecommendVoConverter.class);
}
