package com.ms.blogserver.schedul;

import com.ms.blogserver.core.constant.contexts.DigitalContexts;
import com.ms.blogserver.core.constant.contexts.ErrorContexts;
import com.ms.blogserver.core.constant.contexts.RedisKeyContexts;
import com.ms.blogserver.core.exception.ProgramException;
import com.ms.blogserver.model.bo.ArticleWatchBO;
import com.ms.blogserver.model.entity.Article;
import com.ms.blogserver.service.entity.ArticleService;
import com.ms.blogserver.utils.JsonUtils;
import com.ms.blogserver.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zhh
 * @time: 2022/3/27
 */
@Component
@Slf4j
public class ArticleReadTask {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0 */2 * * ?")
    public void execute() {
        String readJson = (String) redisUtils.get(RedisKeyContexts.ARTICLE_WATCH);
        List<ArticleWatchBO> bos = JsonUtils.toList(readJson, ArticleWatchBO.class);
        Map<Long,Integer> articles = new HashMap<>();
        bos.forEach(item -> {
            Long id = item.getArticleId();
            if (articles.containsKey(id)){
                articles.remove(id, articles.get(id) + 1);
            } else {
                articles.put(id, DigitalContexts.ONE);
            }
        });
        for (Map.Entry<Long,Integer> entry: articles.entrySet()){
            Article article = articleService.getById(entry.getKey());
            article.setRead(article.getRead() + entry.getValue());
            boolean update = articleService.updateById(article);
            if (!update){
                throw new ProgramException(ErrorContexts.DATABASE_UPDATED);
            }
        }
    }
}
