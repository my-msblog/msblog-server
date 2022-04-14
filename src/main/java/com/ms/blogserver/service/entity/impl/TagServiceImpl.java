package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.blogserver.converter.bo.ArticleBoConverter;
import com.ms.blogserver.converter.vo.ArticleCategoryVoConverter;
import com.ms.blogserver.converter.vo.TagVoConverter;
import com.ms.blogserver.mapper.TagMapper;
import com.ms.blogserver.model.bo.ArticleBO;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.entity.ArticleTag;
import com.ms.blogserver.model.entity.Tag;
import com.ms.blogserver.model.vo.ArticleCategoryVO;
import com.ms.blogserver.model.vo.TagVO;
import com.ms.blogserver.service.entity.ArticleService;
import com.ms.blogserver.service.entity.ArticleTagService;
import com.ms.blogserver.service.entity.CategoryService;
import com.ms.blogserver.service.entity.TagService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<TagVO> getTagList() {
        List<Tag> tagList = baseMapper.selectList(new QueryWrapper<>());
        return TagVoConverter.INSTANCE.toDataList(tagList);
    }

    @Override
    public List<ArticleCategoryVO> getTagListById(Long id) {
        List<ArticleTag> articleTagList = articleTagService.lambdaQuery().eq(Objects.nonNull(id), ArticleTag::getTagId, id).list();
        if (CollectionUtils.isEmpty(articleTagList)) {
            return new ArrayList<>();
        }
        List<Long> articleIdList = articleTagList.stream().map(ArticleTag::getArticleId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(articleIdList)) {
            return new ArrayList<>();
        }
        List<Article> articleList = articleService.lambdaQuery().in(Article::getId, articleTagList).list();
        List<ArticleBO> articleBOList = ArticleBoConverter.INSTANCE.toDataList(articleList);
        articleBOList.forEach(item ->{
            String type = categoryService.getCategoryByCid(item.getType());
            item.setTypeName(type);
            List<Long> tagIds = articleTagService
                    .list(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, item.getId()))
                    .stream()
                    .map(ArticleTag::getTagId)
                    .collect(Collectors.toList());
            List<TagVO> tagVOList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(tagIds)){
                List<Tag> tagList = list(new LambdaQueryWrapper<Tag>().in(Tag::getId, tagIds));
                tagVOList = TagVoConverter.INSTANCE.toDataList(tagList);
            }
            item.setTagVOList(tagVOList);
        });
        return ArticleCategoryVoConverter.INSTANCE.toDataList(articleBOList);
    }
}
