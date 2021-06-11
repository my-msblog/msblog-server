package com.ms.blogserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
@ToString
@TableName(value = "ms_")
public class ArticleTag {
    private Long id;
    private Long articleId;
    private Long tagId;
}
