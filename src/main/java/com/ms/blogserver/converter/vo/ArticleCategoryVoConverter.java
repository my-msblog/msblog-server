package com.ms.blogserver.converter.vo;

import com.ms.blogserver.converter.Converter;
import com.ms.blogserver.model.bo.ArticleBO;
import com.ms.blogserver.model.vo.ArticleCategoryVO;
import org.mapstruct.factory.Mappers;

/**
 * @description:
 * @author: zhh
 * @time: 2021/12/16
 */
@org.mapstruct.Mapper
public interface ArticleCategoryVoConverter extends Converter<ArticleBO, ArticleCategoryVO> {

    ArticleCategoryVoConverter INSTANCE = Mappers.getMapper(ArticleCategoryVoConverter.class);
}
