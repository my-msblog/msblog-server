package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
@ToString
@TableName(value = "ms_")
public class ArticleTag implements Serializable {
    private Long id;
    private Long articleId;
    private Long tagId;
}
