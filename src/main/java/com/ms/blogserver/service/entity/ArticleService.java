package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.model.dto.ArticleDTO;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.vo.ArticleVO;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/1
 */
public interface ArticleService extends IService<Article> {

    /**
     * 获取id文章
     * @param id
     * @return
     */
    ArticleVO getArticleById(Long id);

    /**
     * 文章分页
     * @param dto
     * @return
     */
    PageInfo<ArticleVO> getPage(ArticleDTO dto);

}
