package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.model.entity.Tag;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
public interface TagService extends IService<Tag> {
    /**
     * 获取所有文章标签
     *
     * @return
     */
    List<Tag> getAllTag();

    /**
     * 根据标签id获取tag
     *
     * @param id
     * @return
     */
    Tag getTagById(Long id);
}
