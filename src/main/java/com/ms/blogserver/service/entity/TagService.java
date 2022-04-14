package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.model.entity.Tag;
import com.ms.blogserver.model.vo.ArticleCategoryVO;
import com.ms.blogserver.model.vo.TagVO;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
public interface TagService extends IService<Tag> {

    /**
     * 获取标签列表
     *
     * @return
     */
    List<TagVO> getTagList();

    /**
     * 被id标记列表
     *
     * @param id id
     * @return {@link List}<{@link ArticleCategoryVO}>
     */
    List<ArticleCategoryVO> getTagListById(Long id);
}
