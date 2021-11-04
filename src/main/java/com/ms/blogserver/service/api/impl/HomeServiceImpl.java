package com.ms.blogserver.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.converter.bo.ArticleCardBoConverter;
import com.ms.blogserver.converter.vo.ArticleCardVoConverter;
import com.ms.blogserver.converter.vo.TagVoConverter;
import com.ms.blogserver.model.bo.ArticleCardBO;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.entity.ArticleTag;
import com.ms.blogserver.model.entity.Tag;
import com.ms.blogserver.model.vo.ArticleCardVO;
import com.ms.blogserver.model.vo.TagVO;
import com.ms.blogserver.service.api.HomeService;
import com.ms.blogserver.service.entity.ArticleService;
import com.ms.blogserver.service.entity.ArticleTagService;
import com.ms.blogserver.service.entity.CategoryService;
import com.ms.blogserver.service.entity.TagService;
import com.ms.blogserver.utils.PageInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageInfo<ArticleCardVO> getPage(BaseDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getSize());
        List<Article> articleList = articleService.list();
        List<ArticleCardBO> articleCardBOList = ArticleCardBoConverter.INSTANCE.toDataList(articleList);
        articleCardBOList.forEach(articleCardBO -> {
            String type = categoryService.getCategoryByCid(articleCardBO.getType());
            articleCardBO.setTypeName(type);
            List<Long> tagIds = articleTagService
                    .list(new LambdaQueryWrapper<ArticleTag>()
                            .eq(ArticleTag::getArticleId, articleCardBO.getId()))
                    .stream()
                    .map(ArticleTag::getTagId)
                    .collect(Collectors.toList());
            List<Tag> tagList = tagService.list(new LambdaQueryWrapper<Tag>().in(Tag::getId, tagIds));
            List<TagVO> tagVOList = TagVoConverter.INSTANCE.toDataList(tagList);
            articleCardBO.setTagVOList(tagVOList);
        });
        List<ArticleCardVO> resList = ArticleCardVoConverter.INSTANCE.toDataList(articleCardBOList);
        PageInfo<ArticleCardVO> res = new PageInfo<>();
        PageInfoUtil.transform(new PageInfo<>(articleList), res);
        res.setList(resList);
        return res;
    }
}
