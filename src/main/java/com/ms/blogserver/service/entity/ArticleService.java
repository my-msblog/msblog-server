package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.vo.ArchiveVO;
import com.ms.blogserver.model.vo.ArticleCategoryVO;
import com.ms.blogserver.model.vo.ArticleRecommendVO;
import com.ms.blogserver.model.vo.ArticleVO;

import java.util.List;

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
     * 文章归档分页
     * @param dto
     * @return
     */
    PageInfo<ArchiveVO> getPageByTimesLine(BaseDTO dto);

    /**
     * 获取分类文章列表
     * @param category
     * @return
     */
    List<ArticleCategoryVO> getArticleListByCategory(Integer category);

    /**
     * 推荐
     *
     * @return {@link List}<{@link ArticleRecommendVO}>
     */
    List<ArticleRecommendVO> recommend();

    /**
     * 文章点赞
     *
     * @param id id
     */
    void like(Long id);

}
