package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.converter.bo.ArticleBoConverter;
import com.ms.blogserver.converter.vo.ArchiveVoConverter;
import com.ms.blogserver.converter.vo.ArticleCategoryVoConverter;
import com.ms.blogserver.converter.vo.ArticleVoConverter;
import com.ms.blogserver.converter.vo.TagVoConverter;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.model.bo.ArticleBO;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.model.dto.GetCommentDTO;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.entity.ArticleTag;
import com.ms.blogserver.model.entity.Tag;
import com.ms.blogserver.model.vo.*;
import com.ms.blogserver.service.entity.*;
import com.ms.blogserver.utils.PageInfoUtil;
import com.ms.blogserver.mapper.ArticleMapper;
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
 * @time: 2021/6/1
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public ArticleVO getArticleById(Long id) {
        try {
            Article article = baseMapper.selectById(id);
            if (Objects.isNull(article)){
                throw new CustomException("未找到指定文章");
            }
            ArticleVO articleVO = ArticleVoConverter.INSTANCE.toData(article);
            articleVO.setTypeName(categoryService.getCategoryByCid(article.getType()));
            PageInfo<CommentVO> commentVoPageInfo = commentService.getPageByArticle(new GetCommentDTO(id));
            articleVO.setCommentVoS(commentVoPageInfo);
            return articleVO;
        } catch (Exception e) {
           throw new CustomException(e.getMessage());
        }
    }

    @Override
    public PageInfo<ArchiveVO> getPageByTimesLine(BaseDTO dto) {
        PageHelper.startPage(dto.getPage(),dto.getSize());
        List<Article> articles = baseMapper.selectList(
                new LambdaQueryWrapper<Article>().orderByDesc(Article::getCreateTime));
        List<ArchiveVO> archiveVOList = ArchiveVoConverter.INSTANCE.toDataList(articles);
        PageInfo<ArchiveVO> result = new PageInfo<ArchiveVO>();
        PageInfoUtil.transform(new PageInfo<Article>(articles), result);
        result.setList(archiveVOList);
        return result;
    }

    @Override
    public List<ArticleCategoryVO> getArticleListByCategory(Integer category) {
        List<Article> articleListByType = baseMapper.selectList(new LambdaQueryWrapper<Article>()
                .eq(Article::getType, category)
                .orderByDesc(Article::getCreateTime));
        List<ArticleBO> articleBOList = ArticleBoConverter.INSTANCE.toDataList(articleListByType);
        articleBOList.forEach(item ->{
            String type = categoryService.getCategoryByCid(item.getType());
            item.setTypeName(type);
            List<Long> tagIds = articleTagService
                    .list(new LambdaQueryWrapper<ArticleTag>()
                            .eq(ArticleTag::getArticleId, item.getId()))
                    .stream()
                    .map(ArticleTag::getTagId)
                    .collect(Collectors.toList());
            List<TagVO> tagVOList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(tagIds)){
                List<Tag> tagList = tagService.list(new LambdaQueryWrapper<Tag>().in(Tag::getId, tagIds));
                tagVOList = TagVoConverter.INSTANCE.toDataList(tagList);
            }
            item.setTagVOList(tagVOList);
        });
        return ArticleCategoryVoConverter.INSTANCE.toDataList(articleBOList);
    }
}
