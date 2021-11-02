package com.ms.blogserver.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.model.entity.Comment;
import com.ms.blogserver.model.entity.Flow;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.vo.CardValueVO;
import com.ms.blogserver.model.vo.StatisticsVO;
import com.ms.blogserver.service.api.BoardService;
import com.ms.blogserver.service.entity.ArticleService;
import com.ms.blogserver.service.entity.CommentService;
import com.ms.blogserver.service.entity.FlowService;
import com.ms.blogserver.service.entity.UserService;
import com.ms.blogserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/2
 */
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private UserService userService;

    @Autowired
    private FlowService flowService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;


    @Override
    public StatisticsVO getValue() {
        int visit, user, article, comment;
        Date date = DateUtils.getStartTime(new Date());
        visit = flowService.list(new LambdaQueryWrapper<Flow>()
                        .between(Flow::getTime,date, DateUtils.getBeforeMonth(date)))
                .stream()
                .mapToInt(Flow::getFlow)
                .sum();
        user = userService.list().size();
        article = articleService.list(new LambdaQueryWrapper<Article>()
                        .between(Article::getCreateTime,date,DateUtils.getBeforeWeek(date)))
                .size();
        comment = commentService.list(new LambdaQueryWrapper<Comment>()
                        .between(Comment::getCreateTime,date,DateUtils.getBeforeWeek(date)))
                .size();
        CardValueVO value = new CardValueVO(visit,user,article,comment);
        Integer totalVisited = flowService.list().stream().mapToInt(Flow::getFlow).sum();
        Integer totalUser = user;
        Integer totalComment = commentService.list().size();
        Integer totalArticle = articleService.list().size();
        CardValueVO total = new CardValueVO(totalVisited,totalUser,totalArticle,totalComment);
        return new StatisticsVO(value, total);
    }
}
