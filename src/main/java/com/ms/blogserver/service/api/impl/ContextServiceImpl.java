package com.ms.blogserver.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ms.blogserver.converter.vo.ArticleEditVoConverter;
import com.ms.blogserver.converter.vo.TagVoConverter;
import com.ms.blogserver.core.base.BaseOptions;
import com.ms.blogserver.core.constant.contexts.DigitalContexts;
import com.ms.blogserver.model.dto.ArticleCommitDTO;
import com.ms.blogserver.model.dto.IdDTO;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.entity.ArticleTag;
import com.ms.blogserver.model.entity.Category;
import com.ms.blogserver.model.entity.Tag;
import com.ms.blogserver.model.vo.ArticleEditVO;
import com.ms.blogserver.model.vo.TagVO;
import com.ms.blogserver.service.api.ContextService;
import com.ms.blogserver.service.entity.ArticleService;
import com.ms.blogserver.service.entity.ArticleTagService;
import com.ms.blogserver.service.entity.CategoryService;
import com.ms.blogserver.service.entity.TagService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zhh
 * @time: 2022/3/13
 */
@Service
public class ContextServiceImpl implements ContextService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public List<BaseOptions<Integer, String>> getCategory() {
        List<BaseOptions<Integer, String>> resList = new LinkedList<>();
        List<Category> categoryList = categoryService.list();
        categoryList.forEach(category -> {
            BaseOptions<Integer, String> options = new BaseOptions<>();
            options.setLabel(category.getCategory());
            options.setValue(category.getCategoryId());
            resList.add(options);
        });
        return resList;
    }

    @Override
    public List<BaseOptions<Long, String>> getTag() {
        List<BaseOptions<Long, String>> resList = new LinkedList<>();
        List<Tag> tagList = tagService.list();
        tagList.forEach(tag -> {
            resList.add(new BaseOptions<>(tag.getId(), tag.getNameZh()));
        });
        return resList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void commit(ArticleCommitDTO dto) {
        Article article = Article.builder()
                .title(dto.getTitle()).content(dto.getContext())
                .contentMd(dto.getText()).writerId(dto.getWriterId())
                .cover(dto.getCover()).reading(DigitalContexts.ZERO)
                .type(dto.getCategory()).build();
        articleService.save(article);
        Long articleId = articleService.lambdaQuery()
                .select(Article::getId)
                .orderByDesc(Article::getCreateTime)
                .last("limit 1")
                .one()
                .getId();
        List<ArticleTag> articleTagList = new LinkedList<>();
        dto.getTagList().forEach(item -> articleTagList.add(ArticleTag.builder().articleId(articleId).tagId(item).build()));
        articleTagService.saveBatch(articleTagList);
    }

    @Override
    public ArticleEditVO getEditArticle(IdDTO dto) {
        Article article = articleService.getById(dto.getId());
        ArticleEditVO res = ArticleEditVoConverter.INSTANCE.toData(article);
        List<Long> tagIds = articleTagService
                .list(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, article.getId()))
                .stream()
                .map(ArticleTag::getTagId)
                .collect(Collectors.toList());
        List<TagVO> tagVOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(tagIds)){
            List<Tag> tagList = tagService.list(new LambdaQueryWrapper<Tag>().in(Tag::getId, tagIds));
            tagVOList = TagVoConverter.INSTANCE.toDataList(tagList);
        }
        res.setTags(tagVOList);

        return null;
    }
}
