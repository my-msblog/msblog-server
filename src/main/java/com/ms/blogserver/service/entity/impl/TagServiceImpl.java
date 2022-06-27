package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import com.ms.blogserver.converter.bo.ArticleBoConverter;
import com.ms.blogserver.converter.vo.ArticleCategoryVoConverter;
import com.ms.blogserver.converter.vo.TagVoConverter;
import com.ms.blogserver.core.base.EntityServiceImpl;
import com.ms.blogserver.mapper.TagMapper;
import com.ms.blogserver.model.bo.ArticleBO;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.entity.ArticleTag;
import com.ms.blogserver.model.entity.Category;
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

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Service
public class TagServiceImpl extends EntityServiceImpl<Tag,TagMapper> implements TagService {

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
        List<Article> articleList = articleService.lambdaQuery().in(Article::getId, articleIdList).list();
        if (CollectionUtils.isEmpty(articleList)){
            return new ArrayList<>();
        }
        List<ArticleBO> articleBOList = ArticleBoConverter.INSTANCE.toDataList(articleList);
        Map<Integer, String> typeMap = SimpleQuery.map(categoryService.lambdaQueryWrapper().in(Category::getCategoryId,
                        articleBOList.stream().map(ArticleBO::getType).collect(Collectors.toList())),
                Category::getCategoryId,
                Category::getCategory);
        Map<Long, List<Long>> tagIdMap = SimpleQuery.group(Wrappers.<ArticleTag>lambdaQuery()
                        .in(ArticleTag::getArticleId, articleIdList),
                ArticleTag::getArticleId, Collectors.mapping(ArticleTag::getTagId, Collectors.toList()));
        Set<Long> idSet = new HashSet<>();
        tagIdMap.forEach((k,v) -> idSet.addAll(v));
        Map<Long, Tag> tagMap = SimpleQuery.keyMap(lambdaQueryWrapper(Tag.class).in(Tag::getId, idSet), Tag::getId);
        articleBOList.forEach(item ->{
            String type = typeMap.get(item.getType());
            item.setTypeName(type);
            List<Long> tagIds = tagIdMap.get(item.getId());
            List<TagVO> tagVOList = new LinkedList<>();
            for (Long tagId : tagIds){
                Tag tag = tagMap.get(tagId);
                TagVO tagVO = TagVoConverter.INSTANCE.toData(tag);
                tagVOList.add(tagVO);
            }
            item.setTagVOList(tagVOList);
        });
        return ArticleCategoryVoConverter.INSTANCE.toDataList(articleBOList);
    }
}
