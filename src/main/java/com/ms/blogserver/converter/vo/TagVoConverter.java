package com.ms.blogserver.converter.vo;

import com.ms.blogserver.core.base.Converter;
import com.ms.blogserver.model.entity.Tag;
import com.ms.blogserver.model.vo.TagVO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
@org.mapstruct.Mapper
public interface TagVoConverter extends Converter<Tag, TagVO> {

    TagVoConverter INSTANCE = Mappers.getMapper(TagVoConverter.class);

    /**
     * 数据
     *
     * @param tag 标签
     * @return {@link TagVO}
     */
    @Override
    @Mappings({
            @Mapping(source="id",target="tagId")
    })
    TagVO toData(Tag tag);

    /**
     * 重写vo转化
     *
     * @param tags 标签
     * @return {@link List}<{@link TagVO}>
     */
    @Override
    @Mappings({
            @Mapping(source="id",target="tagId")
    })
    List<TagVO> toDataList(List<Tag> tags);
}
