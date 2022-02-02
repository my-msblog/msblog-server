package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.converter.bo.CommentBoConverter;
import com.ms.blogserver.converter.vo.CommentVoConverter;
import com.ms.blogserver.core.constant.contexts.DigitalContexts;
import com.ms.blogserver.core.constant.contexts.ErrorContexts;
import com.ms.blogserver.core.constant.contexts.LoginContexts;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.core.exception.ProgramException;
import com.ms.blogserver.mapper.CommentMapper;
import com.ms.blogserver.model.bo.CommentBO;
import com.ms.blogserver.model.bo.CommentSubmitBO;
import com.ms.blogserver.model.dto.CommentSubmitDTO;
import com.ms.blogserver.model.dto.GiveLikesDTO;
import com.ms.blogserver.model.dto.IdDTO;
import com.ms.blogserver.model.entity.Comment;
import com.ms.blogserver.model.entity.CommentLike;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.vo.CommentItemVO;
import com.ms.blogserver.service.entity.CommentLikeService;
import com.ms.blogserver.service.entity.CommentService;
import com.ms.blogserver.service.entity.UserService;
import com.ms.blogserver.utils.PageInfoUtil;
import com.ms.blogserver.utils.RedisUtils;
import com.ms.blogserver.utils.TokenUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentLikeService commentLikeService;

    @Autowired
    private RedisUtils redisUtils;

    private Long userId;

    @Override
    public List<CommentItemVO> getByParentId(Long pid) {
        List<Comment> list = baseMapper.selectList(new QueryWrapper<Comment>().eq("parent_id", pid));
        List<CommentBO> commentBOList = CommentBoConverter.INSTANCE.toDataList(list);
        commentBOList.forEach(this::handleInfo);
        return CommentVoConverter.INSTANCE.toDataList(commentBOList);
    }

    @Override
    public PageInfo<CommentItemVO> getPageByArticle(IdDTO dto, String token) {
        if (token == null){
            userId = 0L;
        }else {
            userId = Long.getLong(TokenUtils.getAccount(token));
        }
        PageHelper.startPage(dto.getPage(), dto.getSize());
        List<Comment> allComment = baseMapper.selectList(new QueryWrapper<Comment>()
                .lambda().eq(Comment::getArticleId, dto.getId()).eq(Comment::getParentId, DigitalContexts.ZERO)
                .orderByDesc(Comment::getCreateTime));
        if (CollectionUtils.isEmpty(allComment)){
            return new PageInfo<>(new ArrayList<>());
        }
        List<CommentBO> commentBOList = CommentBoConverter.INSTANCE.toDataList(allComment);
        commentBOList.forEach(this::handleInfo);
        List<CommentItemVO> commentItemVOList =CommentVoConverter.INSTANCE.toDataList(commentBOList);
        handle(commentItemVOList);
        PageInfo<CommentItemVO> commentVoPageInfo = new PageInfo<>();
        PageInfoUtil.transform(new PageInfo<>(allComment),commentVoPageInfo);
        commentVoPageInfo.setList(commentItemVOList);
        return commentVoPageInfo;
    }

    @Override
    public void commentSubmit(CommentSubmitDTO dto, String token) {
        if (StringUtils.isEmpty(token)){
            throw new CustomException(LoginContexts.TOKEN_INVALID);
        }
        Long account = Long.getLong(TokenUtils.getAccount(token));
        User curerUser = userService.getById(account);
        CommentSubmitBO commentSubmitBO = CommentSubmitBO.builder()
                .commentId(dto.getCommentId()).context(dto.getContext()).replyTime(dto.getReplyTime())
                .userId(curerUser.getId()).build();
    }

    @Override
    public void commentLike(GiveLikesDTO dto) {
        Map<String, Object> likeMap = redisUtils.hmget(dto.getCommentId().toString());
        if (!likeMap.isEmpty()) {
            GiveLikesDTO commentLikeItem = (GiveLikesDTO)likeMap.get(dto.getUserId().toString());
            if (Objects.isNull(commentLikeItem)) {
                likeMap.put(dto.getUserId().toString(), dto);
            }else{
                commentLikeItem.setIs(!commentLikeItem.getIs());
                likeMap.put(dto.getUserId().toString(), commentLikeItem);
            }
        }
        redisUtils.hmset(dto.getCommentId().toString(), likeMap);
    }

    private void handle(List<CommentItemVO> list){
        list.forEach(commentVO -> {
            List<CommentItemVO> children = getByParentId(commentVO.getId());
            commentVO.setChildren(children);
        });
        list.removeIf(commentVO -> !commentVO.getParentId().equals(DigitalContexts.ZERO_LONG));
    }

    private void handleInfo(CommentBO item){
        User commenter = userService.getById(item.getCommenterId());
        if (Objects.isNull(commenter)){
            throw new ProgramException(ErrorContexts.DATABASE_INNER_TABLE);
        }
        item.setCommenter(commenter.getUsername());
        if (DigitalContexts.ZERO_LONG.equals(item.getRespondentId())) {
            item.setRespondent("");
        } else {
            item.setRespondent(userService.getById(item.getRespondentId()).getUsername());
        }
        Integer likeCount = (int)commentLikeService.count(new LambdaQueryWrapper<CommentLike>()
                .eq(CommentLike::getCommentId, item.getId()));
        item.setLikes(likeCount);
        CommentLike userLike = commentLikeService
                .lambdaQuery()
                .eq(CommentLike::getCommentId, item.getId())
                .eq(CommentLike::getUserId, userId)
                .one();
        item.setIsLike(Objects.nonNull(userLike));
    }
}
