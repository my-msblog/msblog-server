package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.model.entity.ArticleTag;
import com.ms.blogserver.model.vo.TagVO;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
public interface ArticleTagService extends IService<ArticleTag> {

    /**
     * 获取对应文章下的文章标签
     * @param articleId
     * @return
     */
    List<TagVO> getArticleTagById(Long articleId);
}
