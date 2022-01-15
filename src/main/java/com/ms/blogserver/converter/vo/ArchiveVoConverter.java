package com.ms.blogserver.converter.vo;

import com.ms.blogserver.core.base.Converter;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.vo.ArchiveVO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/12/11
 */
@org.mapstruct.Mapper
public interface ArchiveVoConverter extends Converter<Article, ArchiveVO> {

    ArchiveVoConverter INSTANCE = Mappers.getMapper(ArchiveVoConverter.class);

    /**
     * 转化Article
     * @param article
     * @return
     */
    @Override
    @Mappings({
            @Mapping(target = "context", source = "title"),
            @Mapping(target = "timestamp", source = "createTime")
    })
    ArchiveVO toData(Article article);

    /**
     * ArticleList 转化
     * @param articles
     * @return
     */
    @Override
    @Mappings({
            @Mapping(target = "context", source = "title"),
            @Mapping(target = "timestamp", source = "createTime")
    })
    List<ArchiveVO> toDataList(List<Article> articles);
}
