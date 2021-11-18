package com.ms.blogserver.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.converter.bo.ArticleCardBoConverter;
import com.ms.blogserver.converter.vo.ArticleCardVoConverter;
import com.ms.blogserver.converter.vo.TagVoConverter;
import com.ms.blogserver.exception.ProgramException;
import com.ms.blogserver.model.bo.ArticleCardBO;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.entity.*;
import com.ms.blogserver.model.vo.AnnouncementVO;
import com.ms.blogserver.model.vo.ArticleCardVO;
import com.ms.blogserver.model.vo.HomeCardVO;
import com.ms.blogserver.model.vo.TagVO;
import com.ms.blogserver.service.api.HomeService;
import com.ms.blogserver.service.entity.*;
import com.ms.blogserver.utils.DateUtils;
import com.ms.blogserver.utils.PageInfoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AnnouncementService announcementService;

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
            List<TagVO> tagVOList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(tagIds)){
                List<Tag> tagList = tagService.list(new LambdaQueryWrapper<Tag>().in(Tag::getId, tagIds));
                tagVOList = TagVoConverter.INSTANCE.toDataList(tagList);
            }
            articleCardBO.setTagVOList(tagVOList);
        });
        List<ArticleCardVO> resList = ArticleCardVoConverter.INSTANCE.toDataList(articleCardBOList);
        PageInfo<ArticleCardVO> res = new PageInfo<>();
        PageInfoUtil.transform(new PageInfo<>(articleList), res);
        res.setList(resList);
        return res;
    }

    @Override
    public HomeCardVO getHomeCard() {
        HomeCardVO res = new HomeCardVO();
        res.setArticle(articleService.list().size());
        res.setTag(tagService.list().size());
        res.setCategory(categoryService.list().size());
        return res;
    }

    @Override
    public AnnouncementVO getAnnouncement() {
        AnnouncementVO res = new AnnouncementVO();
        Announcement announcement = announcementService.getOne(
                new LambdaQueryWrapper<Announcement>()
                        .ge(Announcement::getUpdateTime, DateUtils.getBeforeWeek(new Date()))
                        .orderByDesc(Announcement::getUpdateTime)
                        .last("limit 1"));
        if (Objects.nonNull(announcement)) {
            User createUser = userService.getById(announcement.getCreateUser());
            if (Objects.isNull(createUser)){
                throw new ProgramException();
            }
            res.setUser(createUser.getUsername());
            res.setAnnouncement(announcement.getAnnouncement());
            res.setTime(announcement.getUpdateTime());
        }
        return res;
    }
}
