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

    List<TagVO> getArticleTagBy(Long articleId);
}
