package com.ms.blogserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.blogserver.config.exception.CustomException;
import com.ms.blogserver.constant.contexts.ErrorContexts;
import com.ms.blogserver.entity.ArticleTag;
import com.ms.blogserver.mapper.ArticleTagMapper;
import com.ms.blogserver.service.ArticleTagService;
import com.ms.blogserver.vo.TagVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
    @Override
    public List<TagVO> getArticleTagBy(Long articleId) {
        if (Objects.nonNull(articleId)){
            throw new CustomException(ErrorContexts.ID_IS_NULL);
        }
        List<ArticleTag> articleTags = baseMapper.selectList(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId,articleId));
        return null;
    }
}
