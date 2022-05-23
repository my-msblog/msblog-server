package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
@TableName(value = "ms_article_tag")
public class ArticleTag implements Serializable {
    private Long id;
    private Long articleId;
    private Long tagId;
}
