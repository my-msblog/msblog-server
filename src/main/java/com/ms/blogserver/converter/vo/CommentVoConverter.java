package com.ms.blogserver.converter.vo;

import com.ms.blogserver.core.base.Converter;
import com.ms.blogserver.model.bo.CommentBO;
import com.ms.blogserver.model.vo.CommentItemVO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@org.mapstruct.Mapper
public interface CommentVoConverter extends Converter<CommentBO, CommentItemVO> {

    CommentVoConverter INSTANCE = Mappers.getMapper(CommentVoConverter.class);

    /**
     * CommentBO 转化 CommentItemVO
     *
     * @param commentBO bo对象
     * @return 评论vo
     */
    @Override
    @Mappings({
            @Mapping(target = "publishTime", source = "createTime"),
            @Mapping(target = "context", source = "content"),
            @Mapping(target = "publisher", source = "commenter"),
            @Mapping(target = "like", source = "likes")
    })
    CommentItemVO toData(CommentBO commentBO);

    /**
     * list 转化 CommentBO -> CommentItemVO
     *
     * @param commentBoS
     * @return
     */
    @Override
    @Mappings({
            @Mapping(target = "publishTime", source = "createTime"),
            @Mapping(target = "context", source = "content"),
            @Mapping(target = "publisher", source = "commenter"),
            @Mapping(target = "like", source = "likes")
    })
    List<CommentItemVO> toDataList(List<CommentBO> commentBoS);
}
