package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.converter.bo.ArticleBoConverter;
import com.ms.blogserver.converter.vo.*;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.core.base.EntityServiceImpl;
import com.ms.blogserver.core.constant.contexts.ErrorContexts;
import com.ms.blogserver.core.constant.contexts.RedisKeyContexts;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.mapper.ArticleMapper;
import com.ms.blogserver.model.bo.ArticleBO;
import com.ms.blogserver.model.bo.ArticleWatchBO;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.entity.ArticleTag;
import com.ms.blogserver.model.entity.Favorites;
import com.ms.blogserver.model.entity.Tag;
import com.ms.blogserver.model.vo.*;
import com.ms.blogserver.service.entity.*;
import com.ms.blogserver.utils.JsonUtils;
import com.ms.blogserver.utils.PageInfoUtil;
import com.ms.blogserver.utils.RedisUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class ArticleServiceImpl extends EntityServiceImpl<Article,ArticleMapper> implements ArticleService {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ArticleVO getArticleById(Long id) {
        try {
            Article article = baseMapper.selectById(id);
            if (Objects.isNull(article)){
                throw new CustomException(ErrorContexts.NOT_FIND_ARTICLE);
            }
            String watchStr = (String) redisUtils.get(RedisKeyContexts.ARTICLE_WATCH);
            List<ArticleWatchBO> watches = new ArrayList<>();
            if (StringUtils.isNotEmpty(watchStr)){
                watches = JsonUtils.toList(watchStr, ArticleWatchBO.class);
            }
            watches.add(ArticleWatchBO.builder().articleId(article.getId()).build());
            redisUtils.set(RedisKeyContexts.ARTICLE_WATCH,JsonUtils.toJson(watches));
            ArticleVO articleVO = ArticleVoConverter.INSTANCE.toData(article);
            articleVO.setTypeName(categoryService.getCategoryByCid(article.getType()));
            articleVO.setLikes(favoritesService.list(new LambdaQueryWrapper<Favorites>()
                    .eq(Objects.isNull(article.getId()), Favorites::getArticleId, article.getId())).size());
            articleVO.setWordCount(article.getContentMd()
                    .replaceAll("<[^>]*>", "")
                    .length());
            int sum = watches.stream().mapToInt(item -> item.getArticleId().equals(article.getId()) ? 1 : 0).sum();
            articleVO.setReading(articleVO.getReading() + sum);
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
            articleVO.setTags(tagVOList);
            articleVO.setWriter(userService.getById(article.getWriterId()).getUsername());
            return articleVO;
        } catch (Exception e) {
           throw new CustomException(e);
        }
    }

    @Override
    public PageInfo<ArchiveVO> getPageByTimesLine(BaseDTO dto) {
        PageHelper.startPage(dto.getPage(),dto.getSize());
        List<Article> articles = baseMapper.selectList(
                new LambdaQueryWrapper<Article>().orderByDesc(Article::getCreateTime));
        List<ArchiveVO> archiveVOList = ArchiveVoConverter.INSTANCE.toDataList(articles);
        PageInfo<ArchiveVO> result = new PageInfo<>();
        PageInfoUtil.transform(new PageInfo<>(articles), result);
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
                    .list(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, item.getId()))
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

    @Override
    public List<ArticleRecommendVO> recommend() {
        List<Article> list = lambdaQuery()
                .select(Article::getId, Article::getTitle, Article::getCover, Article::getCreateTime)
                .orderByDesc(Article::getCreateTime).last("limit 5")
                .list();
        return RecommendVoConverter.INSTANCE.toDataList(list);
    }

    /**
     * ????????????
     *
     * @param id id
     */
    @Override
    public void like(Long id) {

    }
}
