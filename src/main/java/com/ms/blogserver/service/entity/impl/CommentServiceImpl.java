package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.core.constant.contexts.DigitalContexts;
import com.ms.blogserver.converter.bo.CommentBoConverter;
import com.ms.blogserver.converter.vo.CommentVoConverter;
import com.ms.blogserver.model.dto.CommentSubmitDTO;
import com.ms.blogserver.model.entity.Comment;
import com.ms.blogserver.model.bo.CommentBO;
import com.ms.blogserver.model.dto.GetCommentDTO;
import com.ms.blogserver.model.vo.CommentVO;
import com.ms.blogserver.mapper.CommentMapper;
import com.ms.blogserver.service.entity.CommentService;
import com.ms.blogserver.service.entity.UserService;
import com.ms.blogserver.utils.PageInfoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public List<Comment> getAllCommentByArticleId(Long articleId) {
        return baseMapper.selectList(new QueryWrapper<Comment>().eq("article_id", articleId));
    }

    @Override
    public List<CommentVO> getByParentId(Long pid) {
        List<Comment> list = baseMapper.selectList(new QueryWrapper<Comment>().eq("parent_id", pid));
        List<CommentBO> commentBOList = CommentBoConverter.INSTANCE.toDataList(list);
        return setString(commentBOList);
    }

    @Override
    public PageInfo<CommentVO> getPageByArticle(GetCommentDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getSize());
        List<Comment> allComment = getAllCommentByArticleId(dto.getArticleId());
        if (CollectionUtils.isEmpty(allComment)){
            return new PageInfo<>(new ArrayList<>());
        }
        List<CommentBO> commentBOList = CommentBoConverter.INSTANCE.toDataList(allComment);
        List<CommentVO> commentVOList = setString(commentBOList);
        handle(commentVOList);
        PageInfo<CommentVO> commentVoPageInfo = new PageInfo<>();
        PageInfoUtil.transform(new PageInfo<>(allComment),commentVoPageInfo);
        commentVoPageInfo.setList(commentVOList);
        return commentVoPageInfo;
    }

    @Override
    public PageInfo<CommentVO> commentSubmit(CommentSubmitDTO dto) {
        return null;
    }

    private  List<CommentVO> setString(List<CommentBO> commentBOList) {
        commentBOList.forEach(commentBO -> {
            commentBO.setCommenter(userService.getById(commentBO.getCommenterId()).getUsername());
            Long resId = commentBO.getRespondentId();
            if (!resId.equals(DigitalContexts.ZERO_LONG)){
                commentBO.setRespondent(userService.getById(resId).getUsername());
            }
        });
        return CommentVoConverter.INSTANCE.toDataList(commentBOList);
    }

    private void handle(List<CommentVO> list){
        list.forEach(commentVO -> {
            List<CommentVO> children = getByParentId(commentVO.getId());
            commentVO.setChildren(children);
        });
        list.removeIf(commentVO -> !commentVO.getParentId().equals(DigitalContexts.ZERO_LONG));
    }
}
