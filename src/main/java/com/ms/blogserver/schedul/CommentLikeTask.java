package com.ms.blogserver.schedul;

import com.ms.blogserver.core.constant.contexts.RedisKeyContexts;
import com.ms.blogserver.core.constant.types.LikeEnum;
import com.ms.blogserver.model.dto.GiveLikesDTO;
import com.ms.blogserver.model.entity.CommentLike;
import com.ms.blogserver.service.entity.CommentLikeService;
import com.ms.blogserver.utils.DateUtils;
import com.ms.blogserver.utils.JsonUtils;
import com.ms.blogserver.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zhh
 * @time: 2022/2/3
 */
@Component
@Slf4j
public class CommentLikeTask {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private CommentLikeService commentLikeService;

    /**
     * 评论点赞定时任务
     * 每两个小时执行一次
     */
    @Scheduled(cron = "0 0 */2 * * ?")
    public void execute() {
        log.info(DateUtils.nowString());
        Map<String, Object> likeMap = redisUtils.hmget(RedisKeyContexts.COMMENT_LIKES);
        String likeJson = (String) likeMap.get(RedisKeyContexts.COMMENT_LIKES_LIST);
        List<GiveLikesDTO> commentLikes = JsonUtils.toList(likeJson, GiveLikesDTO.class);
        if (likeMap.isEmpty() || CollectionUtils.isEmpty(commentLikes)) {
            return;
        }
        List<CommentLike> dbList = commentLikeService.list();
        commentLikes.forEach(item -> {
            Integer likeStatus = item.getIs() ? LikeEnum.LIKE.status : LikeEnum.UNLIKE.status;
            if (dbList.stream().noneMatch(db -> db.getCommentId().equals(item.getCommentId())
                    && db.getUserId().equals(item.getUserId()))) {
                commentLikeService.save(CommentLike
                        .builder()
                        .commentId(item.getCommentId())
                        .createTime(item.getTime())
                        .userId(item.getUserId())
                        .isLike(likeStatus)
                        .build()
                );
            } else {
                CommentLike one = commentLikeService.lambdaQuery()
                        .eq(CommentLike::getCommentId, item.getCommentId())
                        .eq(CommentLike::getUserId, item.getUserId())
                        .one();
                if (!one.getIsLike().equals(likeStatus)) {
                    commentLikeService.updateById(CommentLike
                            .builder()
                            .id(one.getId())
                            .commentId(one.getCommentId())
                            .userId(one.getUserId())
                            .createTime(item.getTime())
                            .isLike(likeStatus)
                            .build());
                }
            }
        });
        redisUtils.del(RedisKeyContexts.COMMENT_LIKES);
    }

}
