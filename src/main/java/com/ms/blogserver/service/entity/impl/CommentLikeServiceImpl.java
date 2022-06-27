package com.ms.blogserver.service.entity.impl;

import com.ms.blogserver.core.base.EntityServiceImpl;
import com.ms.blogserver.mapper.CommentLikeMapper;
import com.ms.blogserver.model.entity.CommentLike;
import com.ms.blogserver.service.entity.CommentLikeService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/25
 */
@Service
public class CommentLikeServiceImpl
        extends EntityServiceImpl<CommentLike,CommentLikeMapper> implements CommentLikeService {
}
