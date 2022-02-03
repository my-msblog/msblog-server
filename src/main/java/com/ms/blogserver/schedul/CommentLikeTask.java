package com.ms.blogserver.schedul;

import com.ms.blogserver.service.entity.CommentLikeService;
import com.ms.blogserver.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    @Scheduled(cron = "*/15 * * * * ?")
    public void execute() {
        System.out.println(redisUtils.get("1"));
    }

}
